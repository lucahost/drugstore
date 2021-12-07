package ch.ffhs.drugstore.domain.service;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.data.entity.Transaction;

public class HistoryService {
  @Inject
  public HistoryService() {
    // TODO document why this constructor is empty
  }

  public LiveData<List<Transaction>> getHistory() {
    // Still fakin' the actual data.
    List<Transaction> history = new ArrayList<>();
    history.add(new Transaction(1, 1, 1, new Date(2021, 11, 3, 14, 27), 5));
    history.add(new Transaction(2, 1, 1, new Date(2021, 5, 7, 17, 23), 1));
    history.add(new Transaction(3, 1, 1, new Date(2021, 2, 13, 10, 1), 1));
    history.add(new Transaction(4, 1, 1, new Date(2021, 1, 24, 18, 53), 2));
    return new MutableLiveData<>(history);
  }
}
