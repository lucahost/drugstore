package ch.ffhs.drugstore.domain.service;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.data.dto.DrugDto;
import ch.ffhs.drugstore.data.dto.DrugTypeDto;
import ch.ffhs.drugstore.data.dto.UnitDto;
import ch.ffhs.drugstore.data.repository.DrugRepository;
import ch.ffhs.drugstore.data.repository.DrugTypeRepository;
import ch.ffhs.drugstore.data.repository.UnitRepository;

public class DrugManagementService {
    @Inject DrugRepository drugRepository;
    @Inject DrugTypeRepository drugTypeRepository;
    @Inject UnitRepository unitRepository;

    @Inject
    public DrugManagementService() {
        // TODO document why this constructor is empty
    }

    public LiveData<List<DrugDto>> getAllDrugs() {
        return drugRepository.getAllDrugs();
    }

    public LiveData<List<DrugTypeDto>> getAllDrugTypes() {
        return drugTypeRepository.getAllDrugTypes();
    }

    public LiveData<List<UnitDto>> getAllUnits() {
        return unitRepository.getAllUnits();
    }
}
