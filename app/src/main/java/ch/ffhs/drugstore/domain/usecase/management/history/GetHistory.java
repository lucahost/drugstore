package ch.ffhs.drugstore.domain.usecase.management.history;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.shared.dto.management.history.TransactionDto;
import ch.ffhs.drugstore.domain.service.HistoryService;
import ch.ffhs.drugstore.domain.usecase.UseCase;

public class GetHistory implements UseCase<LiveData<List<TransactionDto>>, Void> {
  @Inject HistoryService historyService;

  @Inject
  public GetHistory(HistoryService historyService) {
    this.historyService = historyService;
  }

  @Override
  public LiveData<List<TransactionDto>> execute(Void unused) {
    return historyService.getHistory();
  }
}
