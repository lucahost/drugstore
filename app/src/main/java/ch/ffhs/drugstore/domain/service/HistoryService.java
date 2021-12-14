package ch.ffhs.drugstore.domain.service;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.data.repository.TransactionRepository;
import ch.ffhs.drugstore.shared.dto.management.history.TransactionDto;

public class HistoryService {
    @Inject
    TransactionRepository transactionRepository;

    @Inject
    public HistoryService() {
        // TODO document why this constructor is empty
    }

    public LiveData<List<TransactionDto>> getHistory() {
        return transactionRepository.getAllTransactions();
    }
}
