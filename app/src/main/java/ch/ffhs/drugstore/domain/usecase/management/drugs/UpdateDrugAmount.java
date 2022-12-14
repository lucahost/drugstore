package ch.ffhs.drugstore.domain.usecase.management.drugs;

import javax.inject.Inject;

import ch.ffhs.drugstore.R;
import ch.ffhs.drugstore.domain.service.DrugManagementService;
import ch.ffhs.drugstore.domain.usecase.UseCase;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugDto;
import ch.ffhs.drugstore.shared.dto.management.drugs.UpdateDrugAmountDto;
import ch.ffhs.drugstore.shared.exceptions.DrugNotFoundException;
import ch.ffhs.drugstore.shared.exceptions.DrugstoreException;
import ch.ffhs.drugstore.shared.exceptions.InsufficientAmountException;

public class UpdateDrugAmount implements UseCase<Void, UpdateDrugAmountDto> {
    @Inject
    DrugManagementService drugManagementService;


    @Inject
    public UpdateDrugAmount(DrugManagementService drugManagementService) {
        this.drugManagementService = drugManagementService;
    }

    @Override
    public Void execute(UpdateDrugAmountDto updateDrugAmountDto) throws DrugstoreException {
        DrugDto drug = drugManagementService.getDrugById(updateDrugAmountDto.getDrugId());
        if (drug == null) {
            throw new DrugNotFoundException(R.string.drug_not_found);
        }
        double newAmount = drug.getStockAmount() + updateDrugAmountDto.getAmount();
        if (newAmount < 0) {
            throw new InsufficientAmountException(R.string.not_enough_stock_amount);
        }
        updateDrugAmountDto.setAmount(newAmount);
        drugManagementService.updateDrugAmount(updateDrugAmountDto);
        return null;
    }
}
