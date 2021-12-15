package ch.ffhs.drugstore.domain.usecase.dispensary;

import javax.inject.Inject;

import ch.ffhs.drugstore.R;
import ch.ffhs.drugstore.domain.service.DispensaryService;
import ch.ffhs.drugstore.domain.service.DrugManagementService;
import ch.ffhs.drugstore.domain.service.HistoryService;
import ch.ffhs.drugstore.domain.usecase.UseCase;
import ch.ffhs.drugstore.shared.dto.management.drugs.UpdateDrugAmountDto;
import ch.ffhs.drugstore.shared.exceptions.DrugNotFoundException;
import ch.ffhs.drugstore.shared.exceptions.DrugstoreException;
import ch.ffhs.drugstore.shared.exceptions.InsufficientAmountException;
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

    // TODO @Luca this should be transactional, use dispenseDrug from DAO make it transactional
    @Override
    public Void execute(SubmitDispenseDto submitDispenseDto) throws DrugstoreException {
        DrugDto drug = drugManagementService.getDrugById(submitDispenseDto.getDrugId());
        if (drug == null) {
            throw new DrugNotFoundException(R.string.drug_not_found);
        }
        TransactionDto transaction = new TransactionDto(submitDispenseDto, drug);
        double newAmount = drug.getStockAmount() - submitDispenseDto.getAmount();
        UpdateDrugAmountDto updateDrugAmountDto = new UpdateDrugAmountDto(drug.getDrugId(),
                newAmount);
        drugManagementService.updateDrugAmount(updateDrugAmountDto);
        historyService.addTransaction(transaction);
        return null;
    }
}