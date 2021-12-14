package ch.ffhs.drugstore.domain.usecase.dispensary;

import javax.inject.Inject;

import ch.ffhs.drugstore.R;
import ch.ffhs.drugstore.domain.service.DispensaryService;
import ch.ffhs.drugstore.domain.service.DrugManagementService;
import ch.ffhs.drugstore.domain.usecase.UseCase;
import ch.ffhs.drugstore.shared.dto.dispensary.SubmitDispenseDto;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugDto;

public class DispenseDrug implements UseCase<Void, SubmitDispenseDto> {
    @Inject
    DispensaryService dispensaryService;

    @Inject
    DrugManagementService drugManagementService;

    @Inject
    public DispenseDrug(DispensaryService dispensaryService, DrugManagementService drugManagementService) {
        this.dispensaryService = dispensaryService;
        this.drugManagementService = drugManagementService;
    }

    @Override
    public Void execute(SubmitDispenseDto submitDispenseDto) throws Exception {
        DrugDto drug = drugManagementService.getDrugById(submitDispenseDto.getDrugId());
        if(drug.getStockAmount() < submitDispenseDto.getAmount()) {
            throw new Exception(String.valueOf(R.string.not_enough_stock_amount));
        }
        dispensaryService.updateDrugAmount(drug.getDrugId(), submitDispenseDto.getAmount());
        return null;
    }
}