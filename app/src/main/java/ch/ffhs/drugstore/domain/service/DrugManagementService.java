package ch.ffhs.drugstore.domain.service;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.R;
import ch.ffhs.drugstore.data.repository.DrugRepository;
import ch.ffhs.drugstore.data.repository.DrugTypeRepository;
import ch.ffhs.drugstore.data.repository.SubstanceRepository;
import ch.ffhs.drugstore.data.repository.UnitRepository;
import ch.ffhs.drugstore.shared.dto.management.drugs.UpdateDrugAmountDto;
import ch.ffhs.drugstore.shared.dto.management.drugs.CreateDrugDto;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugDto;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugTypeDto;
import ch.ffhs.drugstore.shared.dto.management.drugs.EditDrugDto;
import ch.ffhs.drugstore.shared.dto.management.drugs.SubstanceDto;
import ch.ffhs.drugstore.shared.dto.management.drugs.UnitDto;
import ch.ffhs.drugstore.shared.exceptions.DrugNotFoundException;
import ch.ffhs.drugstore.shared.exceptions.DrugstoreException;
import ch.ffhs.drugstore.shared.exceptions.InsufficientAmountException;
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


    public void editDrug(EditDrugDto editDrugDto) {
        DrugDto drugDto = mapper.editDrugDtoToDrugDto(editDrugDto);

        SubstanceDto substance = substanceRepository.getOrCreateSubstanceByTitle(
                drugDto.getSubstance());
        drugDto.setSubstance(String.valueOf(substance.getSubstanceId()));

        drugRepository.editDrug(drugDto);
    }

    public void createDrug(CreateDrugDto createDrugDto) {
        DrugDto drugDto = mapper.createDrugDtoToDrugDto(createDrugDto);

        SubstanceDto substance = substanceRepository.getOrCreateSubstanceByTitle(
                drugDto.getSubstance());
        drugDto.setSubstance(String.valueOf(substance.getSubstanceId()));

        drugRepository.createDrug(drugDto);
    }

    public void updateDrugAmount(UpdateDrugAmountDto updateDrugAmountDto) throws DrugstoreException {
        DrugDto drug = drugRepository.getDrugById(updateDrugAmountDto.getDrugId());
        if (drug == null) {
            throw new DrugNotFoundException(R.string.drug_not_found);
        }
        if (drug.getStockAmount() < updateDrugAmountDto.getAmount()) {
            throw new InsufficientAmountException(R.string.not_enough_stock_amount);
        }

        double newAmount = drug.getStockAmount() + updateDrugAmountDto.getAmount();
        drugRepository.updateDrugAmount(drug.getDrugId(), newAmount);
    }

    public void deleteDrug(Integer drugId) {
        drugRepository.deleteDrugById(drugId);
    }
}
