package ch.ffhs.drugstore.domain.service;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.data.repository.TransactionRepository;
import ch.ffhs.drugstore.shared.dto.management.history.TransactionDto;

public class HistoryService {
    private final TransactionRepository transactionRepository;

    @Inject
    public HistoryService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public LiveData<List<TransactionDto>> getHistory() {
        return transactionRepository.getAllTransactions();
    }
}
