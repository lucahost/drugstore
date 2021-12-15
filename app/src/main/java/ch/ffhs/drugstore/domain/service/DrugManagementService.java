package ch.ffhs.drugstore.domain.service;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.data.repository.DrugRepository;
import ch.ffhs.drugstore.data.repository.DrugTypeRepository;
import ch.ffhs.drugstore.data.repository.UnitRepository;
import ch.ffhs.drugstore.shared.dto.management.drugs.CreateDrugDto;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugDto;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugTypeDto;
import ch.ffhs.drugstore.shared.dto.management.drugs.UnitDto;
import ch.ffhs.drugstore.shared.mappers.DrugstoreMapper;

public class DrugManagementService {
    private final DrugstoreMapper mapper;
    private final DrugRepository drugRepository;
    private final DrugTypeRepository drugTypeRepository;
    private final UnitRepository unitRepository;

    @Inject
    public DrugManagementService(DrugRepository drugRepository,
            DrugTypeRepository drugTypeRepository, UnitRepository unitRepository) {
        this.drugRepository = drugRepository;
        this.drugTypeRepository = drugTypeRepository;
        this.unitRepository = unitRepository;
        mapper = DrugstoreMapper.INSTANCE;
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

    public DrugDto getDrugById(int drugId) {
        return drugRepository.getDrugById(drugId);
    }

    public void addDrug(CreateDrugDto createDrugDto) {
        DrugDto drugDto = mapper.createDrugDtoToDrugDto(createDrugDto);
        drugRepository.addDrug(drugDto);
    }
}
