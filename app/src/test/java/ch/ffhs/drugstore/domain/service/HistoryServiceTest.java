package ch.ffhs.drugstore.domain.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.junit.Test;

import java.util.List;

import ch.ffhs.drugstore.data.repository.TransactionRepository;
import ch.ffhs.drugstore.shared.dto.management.history.TransactionDto;

/**
 * Test-class for HistoryService class
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class HistoryServiceTest {

    /**
     * test methode
     */
    @Test
    public void getHistory() {
        // Setup
        LiveData<List<TransactionDto>> drugs = new MutableLiveData<>();
        TransactionRepository transactionRepository = mock(TransactionRepository.class);
        UserService userService = mock(UserService.class);
        when(transactionRepository.getAllTransactions()).thenReturn(drugs);

        HistoryService historyService = new HistoryService(transactionRepository, userService);

        // Test
        LiveData<List<TransactionDto>> result = historyService.getHistory();

        // Assert
        verify(transactionRepository, times(1)).getAllTransactions();
    }
}