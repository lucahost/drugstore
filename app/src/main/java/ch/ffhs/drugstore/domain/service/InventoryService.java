package ch.ffhs.drugstore.domain.service;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.data.repository.DrugRepository;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugDto;

public class InventoryService {
    @Inject
    DrugRepository drugRepository;

    @Inject
    public InventoryService() {
        // TODO document why this constructor is empty
    }

    public LiveData<List<DrugDto>> getInventory() {
        return drugRepository.getAllDrugs();
    }
}
