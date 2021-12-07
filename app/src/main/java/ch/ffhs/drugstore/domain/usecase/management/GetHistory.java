package ch.ffhs.drugstore.domain.usecase.management;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.data.entity.Transaction;
import ch.ffhs.drugstore.domain.service.HistoryService;
import ch.ffhs.drugstore.domain.usecase.UseCase;

public class GetHistory implements UseCase<LiveData<List<Transaction>>, Void> {
  @Inject HistoryService historyService;

  @Inject
  public GetHistory(HistoryService historyService) {
    this.historyService = historyService;
  }

  @Override
  public LiveData<List<Transaction>> execute(Void unused) {
    return historyService.getHistory();
  }
}
