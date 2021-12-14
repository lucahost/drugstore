package ch.ffhs.drugstore.presentation.management.history.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.domain.usecase.management.history.GetHistory;
import ch.ffhs.drugstore.shared.dto.management.history.TransactionDto;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class HistoryViewModel extends AndroidViewModel {
  @Inject GetHistory getHistory;
  private LiveData<List<TransactionDto>> items;

  @Inject
  public HistoryViewModel(Application application) {
    super(application);
  }

  public LiveData<List<TransactionDto>> getItems() {
    if (items == null) {
      items = getHistory.execute(null);
    }
    return items;
  }
}
