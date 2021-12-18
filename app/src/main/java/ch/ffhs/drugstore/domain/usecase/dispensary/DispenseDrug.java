package ch.ffhs.drugstore.domain.usecase.dispensary;

import javax.inject.Inject;

import ch.ffhs.drugstore.R;
import ch.ffhs.drugstore.domain.service.DrugManagementService;
import ch.ffhs.drugstore.domain.service.HistoryService;
import ch.ffhs.drugstore.domain.usecase.UseCase;
import ch.ffhs.drugstore.shared.dto.dispensary.SubmitDispenseDto;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugDto;
import ch.ffhs.drugstore.shared.dto.management.drugs.UpdateDrugAmountDto;
import ch.ffhs.drugstore.shared.dto.management.history.TransactionDto;
import ch.ffhs.drugstore.shared.exceptions.DrugNotFoundException;
import ch.ffhs.drugstore.shared.exceptions.DrugstoreException;
import ch.ffhs.drugstore.shared.exceptions.InsufficientAmountException;

/**
 * Use-Case class dispense a specific drug
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class DispenseDrug implements UseCase<Void, SubmitDispenseDto> {
    private final DrugManagementService drugManagementService;
    private final HistoryService historyService;

    /**
     * Construct a {@link DispenseDrug} use case
     *
     * @param drugManagementService drug management service
     * @param historyService        history service
     */
    @Inject
    public DispenseDrug(DrugManagementService drugManagementService,
            HistoryService historyService) {
        this.drugManagementService = drugManagementService;
        this.historyService = historyService;
    }

    // TODO @Luca this should be transactional, use dispenseDrug from DAO make it transactional

    /**
     * {@inheritDoc}
     *
     * @param submitDispenseDto submit dispense input dto of the use case
     * @return Void
     * @throws DrugstoreException          if drug not found
     * @throws InsufficientAmountException if not enough amount on stock
     */
    @Override
    public Void execute(SubmitDispenseDto submitDispenseDto) throws DrugstoreException {
        DrugDto drug = drugManagementService.getDrugById(submitDispenseDto.getDrugId());
        TransactionDto transaction = new TransactionDto(submitDispenseDto, drug);

        if (drug == null) {
            throw new DrugNotFoundException(R.string.drug_not_found);
        }

        double newAmount = drug.getStockAmount() - submitDispenseDto.getAmount();
        if (newAmount < 0) {
            throw new InsufficientAmountException(R.string.not_enough_stock_amount);
        }

        UpdateDrugAmountDto updateDrugAmountDto = new UpdateDrugAmountDto(drug.getDrugId(),
                newAmount);
        drugManagementService.updateDrugAmount(updateDrugAmountDto);
        historyService.addTransaction(transaction);
        return null;
    }
}