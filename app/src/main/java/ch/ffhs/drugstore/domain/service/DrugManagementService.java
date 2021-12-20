package ch.ffhs.drugstore.domain.service;

import android.database.sqlite.SQLiteConstraintException;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.R;
import ch.ffhs.drugstore.data.repository.DrugRepository;
import ch.ffhs.drugstore.data.repository.DrugTypeRepository;
import ch.ffhs.drugstore.data.repository.SubstanceRepository;
import ch.ffhs.drugstore.data.repository.UnitRepository;
import ch.ffhs.drugstore.shared.dto.management.drugs.CreateDrugDto;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugDto;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugTypeDto;
import ch.ffhs.drugstore.shared.dto.management.drugs.EditDrugDto;
import ch.ffhs.drugstore.shared.dto.management.drugs.SubstanceDto;
import ch.ffhs.drugstore.shared.dto.management.drugs.UnitDto;
import ch.ffhs.drugstore.shared.dto.management.drugs.UpdateDrugAmountDto;
import ch.ffhs.drugstore.shared.exceptions.DrugAlreadyUsedException;
import ch.ffhs.drugstore.shared.exceptions.DrugNotFoundException;
import ch.ffhs.drugstore.shared.exceptions.DrugstoreException;
import ch.ffhs.drugstore.shared.mappers.DrugstoreMapper;

/**
 * This service class communicates with data layer and is used to manage drugs
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class DrugManagementService {
    private final DrugstoreMapper mapper;
    private final DrugRepository drugRepository;
    private final DrugTypeRepository drugTypeRepository;
    private final SubstanceRepository substanceRepository;
    private final UnitRepository unitRepository;

    /**
     * Construct a {@link DrugManagementService} service
     *
     * @param drugRepository      drug repository
     * @param drugTypeRepository  drug type
     * @param substanceRepository substance repository
     * @param unitRepository      unit repository
     */
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

    /**
     * @return all drugs
     */
    public LiveData<List<DrugDto>> getAllDrugs() {
        return drugRepository.getAllDrugs();
    }

    /**
     * @return all drug types
     */
    public LiveData<List<DrugTypeDto>> getAllDrugTypes() {
        return drugTypeRepository.getAllDrugTypes();
    }

    /**
     * @return all units
     */
    public LiveData<List<UnitDto>> getAllUnits() {
        return unitRepository.getAllUnits();
    }

    /**
     * @return all substances
     */
    public LiveData<List<SubstanceDto>> getAllSubstances() {
        return substanceRepository.getAllSubstances();
    }

    /**
     * gets a drug by id
     *
     * @param drugId id of the drug
     * @return drug from repository
     */
    public DrugDto getDrugById(int drugId) {
        return drugRepository.getDrugById(drugId);
    }

    /**
     * this method edits drugs
     *
     * @param editDrugDto edit drug input dto
     * @throws DrugstoreException if creation of the drug goes wrong
     */
    public void editDrug(EditDrugDto editDrugDto) throws DrugNotFoundException {
        DrugDto originalDrug = drugRepository.getDrugById(editDrugDto.getDrugId());
        if (originalDrug == null) {
            throw new DrugNotFoundException(R.string.drug_not_found);
        }
        DrugDto drugDto = mapper.editDrugDtoToDrugDto(editDrugDto);

        SubstanceDto substance = substanceRepository.getOrCreateSubstanceByTitle(
                drugDto.getSubstance());
        drugDto.setSubstance(String.valueOf(substance.getSubstanceId()));

        // EditDrugDto does not contain stockAmount
        // therefore we need to set it to the original value
        drugDto.setStockAmount(originalDrug.getStockAmount());

        drugRepository.editDrug(drugDto);
    }

    /**
     * this method creates a new drug
     *
     * @param createDrugDto create drug input dto
     */
    public long createDrug(CreateDrugDto createDrugDto) {
        DrugDto drugDto = mapper.createDrugDtoToDrugDto(createDrugDto);

        SubstanceDto substance = substanceRepository.getOrCreateSubstanceByTitle(
                drugDto.getSubstance());
        drugDto.setSubstance(String.valueOf(substance.getSubstanceId()));

        return drugRepository.createDrug(drugDto);
    }

    /**
     * this method updates the amount of a drug
     *
     * @param updateDrugAmountDto update drug input dto
     * @throws DrugstoreException when drug not found
     */
    public void updateDrugAmount(UpdateDrugAmountDto updateDrugAmountDto)
            throws DrugstoreException {
        DrugDto drug = drugRepository.getDrugById(updateDrugAmountDto.getDrugId());
        if (drug == null) {
            throw new DrugNotFoundException(R.string.drug_not_found);
        }

        drugRepository.updateDrugAmount(drug.getDrugId(), updateDrugAmountDto.getAmount());
    }

    /**
     * deletes a drug from database by id
     *
     * @param drugId id of the drug to be deleted
     */
    public void deleteDrug(Integer drugId) throws DrugstoreException {
        try {
            drugRepository.deleteDrugById(drugId);
        } catch (SQLiteConstraintException ex) {
            throw new DrugAlreadyUsedException(R.string.drug_prevente_delete);
        }
    }
}
