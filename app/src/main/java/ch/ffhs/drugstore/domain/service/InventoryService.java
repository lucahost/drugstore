package ch.ffhs.drugstore.domain.service;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.data.repository.DrugRepository;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugDto;

/**
 * This service class communicates with data layer and is used to show the drug inventory
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class InventoryService {
    private final DrugRepository drugRepository;

    /**
     * Construct a {@link InventoryService} service
     *
     * @param drugRepository drug repository
     */
    @Inject
    public InventoryService(DrugRepository drugRepository) {
        this.drugRepository = drugRepository;
    }

    /**
     * Retrieve all inventory items
     *
     * @return all drugs
     */
    public LiveData<List<DrugDto>> getInventory() {
        return drugRepository.getAllDrugs();
    }
}
