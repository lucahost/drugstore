package ch.ffhs.drugstore.domain.usecase.dispensary;

import javax.inject.Inject;

import ch.ffhs.drugstore.R;
import ch.ffhs.drugstore.domain.service.DispensaryService;
import ch.ffhs.drugstore.domain.service.DrugManagementService;
import ch.ffhs.drugstore.domain.service.HistoryService;
import ch.ffhs.drugstore.domain.usecase.UseCase;
import ch.ffhs.drugstore.shared.dto.dispensary.SubmitDispenseDto;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugDto;
import ch.ffhs.drugstore.shared.dto.management.history.TransactionDto;

public class DispenseDrug implements UseCase<Void, SubmitDispenseDto> {
    DispensaryService dispensaryService;
    DrugManagementService drugManagementService;
    HistoryService historyService;

    @Inject
    public DispenseDrug(DispensaryService dispensaryService,
            DrugManagementService drugManagementService,
            HistoryService historyService) {
        this.dispensaryService = dispensaryService;
        this.drugManagementService = drugManagementService;
        this.historyService = historyService;
    }

    // TODO @Luca this should be transactional, if addTransaction fails, so should updateDrug
    @Override
    public Void execute(SubmitDispenseDto submitDispenseDto) throws Exception {
        DrugDto drug = drugManagementService.getDrugById(submitDispenseDto.getDrugId());
        TransactionDto transaction = new TransactionDto(submitDispenseDto, drug);
        if (drug.getStockAmount() < submitDispenseDto.getAmount()) {
            throw new Exception(String.valueOf(R.string.not_enough_stock_amount));
        }
        dispensaryService.updateDrugAmount(drug.getDrugId(), drug.getStockAmount() - submitDispenseDto.getAmount());
        historyService.addTransaction(transaction);
        return null;
    }
}