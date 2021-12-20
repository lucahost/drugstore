package ch.ffhs.drugstore.presentation.management.inventory.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Inject;

import ch.ffhs.drugstore.domain.usecase.management.inventory.GetInventory;
import ch.ffhs.drugstore.domain.usecase.management.inventory.SignInventory;
import ch.ffhs.drugstore.domain.usecase.management.inventory.ToggleInventoryItem;
import ch.ffhs.drugstore.shared.dto.management.drugs.SelectableDrugDto;
import ch.ffhs.drugstore.shared.dto.management.signature.CreateSignatureDto;
import ch.ffhs.drugstore.shared.dto.management.signature.SignatureDrugDto;
import ch.ffhs.drugstore.shared.exceptions.DrugstoreException;
import dagger.hilt.android.lifecycle.HiltViewModel;

/**
 * View model for inventory management.
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
@HiltViewModel
public class InventoryViewModel extends AndroidViewModel {

    private final Map<Integer, SignatureDrugDto> signatureDrugs;
    private final GetInventory getInventory;
    private final ToggleInventoryItem toggleInventoryItem;
    SignInventory signInventory;
    private LiveData<List<SelectableDrugDto>> drugs;

    /**
     * Constructs a {@link InventoryViewModel}
     *
     * @param application         global application state
     * @param getInventory        use case to get inventory
     * @param toggleInventoryItem use case to toggle an inventory item
     * @param signInventory       use case to sign an inventory
     */
    @Inject
    public InventoryViewModel(Application application,
            GetInventory getInventory,
            ToggleInventoryItem toggleInventoryItem,
            SignInventory signInventory) {
        super(application);
        this.getInventory = getInventory;
        this.toggleInventoryItem = toggleInventoryItem;
        this.signInventory = signInventory;
        signatureDrugs = new HashMap<>();
    }

    /**
     * Get all drugs
     *
     * @return drugs
     */
    public LiveData<List<SelectableDrugDto>> getDrugs() {
        if (drugs == null) {
            drugs = Transformations.map(getInventory.execute(null),
                    d -> d.stream().map(dt -> new SelectableDrugDto(dt, false)).collect(
                            Collectors.toList()));
        }
        return drugs;
    }

    /**
     * Get all toggled drugs
     *
     * @return checkedDrugs
     */
    public Map<Integer, SignatureDrugDto> getSignatureDrugs() {
        return signatureDrugs;
    }

    /**
     * Signs the current state of the inventory.
     *
     * @param shortName the short name of the signing employee
     */
    public void signInventory(String shortName) {
        ArrayList<SignatureDrugDto> signatureDrugList = new ArrayList<>(signatureDrugs.values());
        CreateSignatureDto createSignatureDto = new CreateSignatureDto(shortName,
                signatureDrugList);
        signInventory.execute(createSignatureDto);
        signatureDrugs.clear();
    }

    /**
     * Toggle a single inventory item by id
     *
     * @param drugId the id of the inventory item to toggle
     * @throws DrugstoreException if toggling of the inventory item goes wrong
     */
    public void toggleInventoryItem(Integer drugId, boolean selected) throws DrugstoreException {
        if (!selected || signatureDrugs.containsKey(drugId)) {
            signatureDrugs.remove(drugId);
            return;
        }
        signatureDrugs.put(drugId, toggleInventoryItem.execute(drugId));
    }
}
