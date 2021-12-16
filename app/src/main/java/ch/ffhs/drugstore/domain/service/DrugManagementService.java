package ch.ffhs.drugstore.domain.service;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.data.repository.DrugRepository;
import ch.ffhs.drugstore.shared.dto.management.drugs.CreateDrugDto;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugDto;
import ch.ffhs.drugstore.shared.mappers.DrugstoreMapper;
/**
 * This class represents a service which manages drugs
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class DrugManagementService {
    private final DrugstoreMapper mapper;
    private final DrugRepository drugRepository;

    /**
     *
     * @param drugRepository
     */
    @Inject
    public DrugManagementService(DrugRepository drugRepository) {
        this.drugRepository = drugRepository;
        mapper = DrugstoreMapper.INSTANCE;
    }

    /**
     *
     * @return
     */
    public LiveData<List<DrugDto>> getAllDrugs() {
        return drugRepository.getAllDrugs();
    }

    /**
     *
     * @param drugId
     * @return
     */
    public DrugDto getDrugById(int drugId) {
        return drugRepository.getDrugById(drugId);
    }

    /**
     *
     * @param createDrugDto
     */
    public void addDrug(CreateDrugDto createDrugDto) {
        DrugDto drugDto = mapper.createDrugDtoToDrugDto(createDrugDto);
        drugRepository.addDrug(drugDto);
    }
}
