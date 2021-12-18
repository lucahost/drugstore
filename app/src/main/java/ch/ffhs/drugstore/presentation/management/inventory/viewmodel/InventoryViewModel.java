package ch.ffhs.drugstore.presentation.management.inventory.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import ch.ffhs.drugstore.domain.usecase.management.inventory.GetInventory;
import ch.ffhs.drugstore.domain.usecase.management.inventory.SignInventory;
import ch.ffhs.drugstore.domain.usecase.management.inventory.ToggleInventoryItem;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugDto;
import ch.ffhs.drugstore.shared.dto.management.signature.CreateSignatureDto;
import ch.ffhs.drugstore.shared.dto.management.signature.SignatureDrugDto;
import ch.ffhs.drugstore.shared.exceptions.DrugstoreException;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class InventoryViewModel extends AndroidViewModel {
    private final Map<Integer, SignatureDrugDto> signatureDrugs;
    @Inject
    GetInventory getInventory;
    @Inject
    ToggleInventoryItem toggleInventoryItem;
    @Inject
    SignInventory signInventory;
    private LiveData<List<DrugDto>> items;

    @Inject
    public InventoryViewModel(Application application) {
        super(application);
        signatureDrugs = new HashMap<>();
    }

    public LiveData<List<DrugDto>> getItems() {
        if (items == null) {
            items = getInventory.execute(null);
        }
        return items;
    }

    public void signInventory(String shortName) {
        ArrayList<SignatureDrugDto> signatureDrugList = new ArrayList<>(signatureDrugs.values());
        CreateSignatureDto createSignatureDto = new CreateSignatureDto(shortName,
                signatureDrugList);
        signInventory.execute(createSignatureDto);
        signatureDrugs.clear();
    }

    public void toggleInventoryItem(Integer drugId) throws DrugstoreException {
        if (signatureDrugs.get(drugId) != null) {
            signatureDrugs.remove(drugId);
        } else {
            signatureDrugs.put(drugId, toggleInventoryItem.execute(drugId));
        }
    }
}
