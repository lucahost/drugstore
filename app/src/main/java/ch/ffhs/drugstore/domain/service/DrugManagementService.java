package ch.ffhs.drugstore.domain.service;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.data.repository.DrugRepository;
import ch.ffhs.drugstore.data.repository.DrugTypeRepository;
import ch.ffhs.drugstore.data.repository.SubstanceRepository;
import ch.ffhs.drugstore.data.repository.UnitRepository;
import ch.ffhs.drugstore.shared.dto.management.drugs.AddDrugDto;
import ch.ffhs.drugstore.shared.dto.management.drugs.CreateDrugDto;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugDto;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugTypeDto;
import ch.ffhs.drugstore.shared.dto.management.drugs.SubstanceDto;
import ch.ffhs.drugstore.shared.dto.management.drugs.UnitDto;
import ch.ffhs.drugstore.shared.mappers.DrugstoreMapper;

public class DrugManagementService {
    private final DrugstoreMapper mapper;
    private final DrugRepository drugRepository;
    private final DrugTypeRepository drugTypeRepository;
    private final SubstanceRepository substanceRepository;
    private final UnitRepository unitRepository;

    @Inject
    public DrugManagementService(DrugRepository drugRepository,
            DrugTypeRepository drugTypeRepository, SubstanceRepository substanceRepository,
            UnitRepository unitRepository) {
        this.drugRepository = drugRepository;
        this.drugTypeRepository = drugTypeRepository;
        this.substanceRepository = substanceRepository;
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

    public Void createDrug(CreateDrugDto createDrugDto) {
        DrugDto drugDto = mapper.createDrugDtoToDrugDto(createDrugDto);

        SubstanceDto substance = substanceRepository.getOrCreateSubstanceByTitle(
                drugDto.getSubstance());
        drugDto.setSubstance(String.valueOf(substance.getSubstanceId()));

        return drugRepository.createDrug(drugDto);
    }

    public Void addDrug(AddDrugDto addDrugDto) {
        return null;
    }

    public Void deleteDrug(Integer drugId) {
        drugRepository.deleteDrugById(drugId);
        return null;
    }
}
