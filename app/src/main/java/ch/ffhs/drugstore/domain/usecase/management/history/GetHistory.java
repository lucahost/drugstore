package ch.ffhs.drugstore.domain.usecase.management.history;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.domain.service.HistoryService;
import ch.ffhs.drugstore.domain.usecase.UseCase;
import ch.ffhs.drugstore.shared.dto.management.history.TransactionDto;

/**
 * Use-Case class to get the transaction history of a drug
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class GetHistory implements UseCase<LiveData<List<TransactionDto>>, Void> {
  @Inject HistoryService historyService;

  @Inject
  public GetHistory(HistoryService historyService) {
    this.historyService = historyService;
  }

  /**
   * gets transaction history
   * @return all transactions
   */
  @Override
  public LiveData<List<TransactionDto>> execute(Void unused) {
    return historyService.getHistory();
  }
}
