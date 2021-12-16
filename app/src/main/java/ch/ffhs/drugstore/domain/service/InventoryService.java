package ch.ffhs.drugstore.domain.service;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.data.repository.DrugRepository;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugDto;

/**
 * This class represents a service to return drugs
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class InventoryService {
    private final DrugRepository drugRepository;


    /**
     * Construct the service
     */
    @Inject
    public InventoryService(DrugRepository drugRepository) {
        this.drugRepository = drugRepository;
    }

    /**
     *
     * @return  A list of drug-objects
     */
    public LiveData<List<DrugDto>> getInventory() {
        return drugRepository.getAllDrugs();
    }
}
