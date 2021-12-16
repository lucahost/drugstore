package ch.ffhs.drugstore.domain.service;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.shared.dto.management.history.TransactionDto;
import ch.ffhs.drugstore.data.repository.TransactionRepository;
/**
 * This class represents a service to get a history of the transactions
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class HistoryService {
    private final TransactionRepository transactionRepository;

    /**
     * construct the service
     * @param transactionRepository
     */
    @Inject
    public HistoryService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    /**
     *
     * @return
     */
    public LiveData<List<TransactionDto>> getHistory() {
        return transactionRepository.getAllTransactions();
    }
}
