package ch.ffhs.drugstore.domain.service;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.data.repository.DrugRepository;
import ch.ffhs.drugstore.shared.dto.management.drugs.CreateDrugDto;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugDto;
import ch.ffhs.drugstore.shared.mappers.DrugstoreMapper;

public class DrugManagementService {
    private final DrugstoreMapper mapper;
    @Inject
    DrugRepository drugRepository;

    @Inject
    public DrugManagementService() {
        // TODO document why this constructor is empty
        mapper = DrugstoreMapper.INSTANCE;
    }

    public LiveData<List<DrugDto>> getAllDrugs() {
        return drugRepository.getAllDrugs();
    }

    public DrugDto getDrugById(int drugId) {
        return drugRepository.getDrugById(drugId);
    }

    public void addDrug(CreateDrugDto createDrugDto) {
        DrugDto drugDto = mapper.createDrugDtoToDrugDto(createDrugDto);
        drugRepository.addDrug(drugDto);
    }
}
