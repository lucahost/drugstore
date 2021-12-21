package ch.ffhs.drugstore.domain.usecase.management.drugs;

import java.time.ZonedDateTime;

import javax.inject.Inject;

import ch.ffhs.drugstore.R;
import ch.ffhs.drugstore.domain.service.DrugManagementService;
import ch.ffhs.drugstore.domain.service.HistoryService;
import ch.ffhs.drugstore.domain.usecase.UseCase;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugDto;
import ch.ffhs.drugstore.shared.dto.management.drugs.UpdateDrugAmountDto;
import ch.ffhs.drugstore.shared.dto.management.history.TransactionDto;
import ch.ffhs.drugstore.shared.dto.management.user.UserDto;
import ch.ffhs.drugstore.shared.exceptions.DrugNotFoundException;
import ch.ffhs.drugstore.shared.exceptions.DrugstoreException;
import ch.ffhs.drugstore.shared.exceptions.InsufficientAmountException;
import ch.ffhs.drugstore.shared.exceptions.InvalidUserException;

/**
 * Use-Case class to update the amount of a specific drug
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class UpdateDrugAmount implements UseCase<Void, UpdateDrugAmountDto> {
    private final DrugManagementService drugManagementService;
    private final HistoryService historyService;

    /**
     * Construct a {@link UpdateDrugAmount} use case
     *
     * @param drugManagementService drug management service
     * @param historyService        history service
     */
    @Inject
    public UpdateDrugAmount(DrugManagementService drugManagementService,
            HistoryService historyService) {
        this.drugManagementService = drugManagementService;
        this.historyService = historyService;
    }

    /**
     * {@inheritDoc}
     *
     * @param updateDrugAmountDto update drug amount input dto of the use case
     * @return Void
     * @throws DrugstoreException          if drug not found
     * @throws InsufficientAmountException if amount insufficient
     */
    @Override
    public Void execute(UpdateDrugAmountDto updateDrugAmountDto) throws DrugstoreException {
        String userShortName = updateDrugAmountDto.getUserShortName();
        if (userShortName == null || userShortName.isEmpty()) {
            throw new InvalidUserException(R.string.error_employee_required);
        }
        DrugDto drug = drugManagementService.getDrugById(updateDrugAmountDto.getDrugId());
        if (drug == null) {
            throw new DrugNotFoundException(R.string.drug_not_found);
        }

        double amountToAddOrRemove = updateDrugAmountDto.getAmount();
        double newAmount = drug.getStockAmount() + amountToAddOrRemove;
        if (newAmount < 0) {
            throw new InsufficientAmountException(R.string.not_enough_stock_amount);
        }
        updateDrugAmountDto.setAmount(newAmount);
        drugManagementService.updateDrugAmount(updateDrugAmountDto);

        // Insert 'TO SYSTEM' Transaction in History
        UserDto user = new UserDto();
        user.setShortName(userShortName);
        TransactionDto transactionDto = new TransactionDto(null, drug, user, ZonedDateTime.now(),
                amountToAddOrRemove, "SYSTEM");
        historyService.addTransaction(transactionDto);

        return null;
    }
}
