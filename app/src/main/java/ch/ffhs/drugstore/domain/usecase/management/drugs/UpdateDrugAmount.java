package ch.ffhs.drugstore.domain.usecase.management.drugs;

import javax.inject.Inject;

import ch.ffhs.drugstore.domain.service.DrugManagementService;
import ch.ffhs.drugstore.domain.usecase.UseCase;
import ch.ffhs.drugstore.shared.dto.management.drugs.UpdateDrugAmountDto;
import ch.ffhs.drugstore.shared.exceptions.DrugstoreException;

public class UpdateDrugAmount implements UseCase<Void, UpdateDrugAmountDto> {
    @Inject
    DrugManagementService drugManagementService;


    @Inject
    public UpdateDrugAmount(DrugManagementService drugManagementService) {
        this.drugManagementService = drugManagementService;
    }

    @Override
    public Void execute(UpdateDrugAmountDto updateDrugAmountDto) throws DrugstoreException {
        drugManagementService.updateDrugAmount(updateDrugAmountDto);
        return null;
    }
}
