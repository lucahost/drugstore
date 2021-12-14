package ch.ffhs.drugstore.domain.service;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.data.repository.DrugRepository;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugDto;

public class InventoryService {
    private final DrugRepository drugRepository;

    @Inject
    public InventoryService(DrugRepository drugRepository) {
        this.drugRepository = drugRepository;
    }

    public LiveData<List<DrugDto>> getInventory() {
        return drugRepository.getAllDrugs();
    }
}
