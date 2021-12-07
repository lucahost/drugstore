package ch.ffhs.drugstore.presentation.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.data.entity.Transaction;
import ch.ffhs.drugstore.domain.usecase.management.GetHistory;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class HistoryViewModel extends AndroidViewModel {
  @Inject GetHistory getHistory;
  private LiveData<List<Transaction>> items;

  @Inject
  public HistoryViewModel(Application application) {
    super(application);
  }

  public LiveData<List<Transaction>> getItems() {
    if (items == null) {
      items = getHistory.execute(null);
    }
    return items;
  }
}
