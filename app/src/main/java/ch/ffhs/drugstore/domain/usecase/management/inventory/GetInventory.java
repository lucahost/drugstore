package ch.ffhs.drugstore.domain.usecase.management.inventory;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.domain.service.InventoryService;
import ch.ffhs.drugstore.domain.usecase.UseCase;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugDto;

/**
 * Use-Case class to get the current drug inventory
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class GetInventory implements UseCase<LiveData<List<DrugDto>>, Void> {
    private final InventoryService inventoryService;

    /**
     * Construct a {@link GetInventory} use case
     *
     * @param inventoryService inventory service
     */
    @Inject
    public GetInventory(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    /**
     * {@inheritDoc}
     *
     * @param unused no parameters
     * @return live data list of drugs
     */
    @Override
    public LiveData<List<DrugDto>> execute(Void unused) {
        return inventoryService.getInventory();
    }
}
