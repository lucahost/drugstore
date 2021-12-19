package ch.ffhs.drugstore.domain.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.github.javafaker.Faker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import ch.ffhs.drugstore.data.repository.DrugRepository;
import ch.ffhs.drugstore.data.repository.TransactionRepository;
import ch.ffhs.drugstore.shared.dto.management.history.TransactionDto;
import ch.ffhs.drugstore.shared.dto.management.user.UserDto;

/**
 * Test-class for HistoryService class
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class HistoryServiceTest {

    private UserService userService = null;
    private TransactionRepository transactionRepository = null;
    private final Faker faker = new Faker();

    @Before
    public void setUp() throws Exception {
        userService = mock(UserService.class);
        transactionRepository = mock(TransactionRepository.class);
    }

    @After
    public void tearDown() throws Exception {
        userService = null;
        transactionRepository = null;
    }

    /**
     * test method
     */
    @Test
    public void getHistory() {
        // Setup
        LiveData<List<TransactionDto>> drugs = new MutableLiveData<>();
        when(transactionRepository.getAllTransactions()).thenReturn(drugs);

        HistoryService historyService = new HistoryService(transactionRepository, userService);

        // Test
        LiveData<List<TransactionDto>> result = historyService.getHistory();

        // Assert
        verify(transactionRepository, times(1)).getAllTransactions();
    }

    @Test
    public void addTransactionWithUser() {
        // Setup
        TransactionDto transactionDto = mock(TransactionDto.class);
        UserDto userDto = mock(UserDto.class);
        when(userDto.getUserId()).thenReturn(faker.number().randomDigit());
        when(transactionDto.getUser()).thenReturn(userDto);
        HistoryService historyService = new HistoryService(transactionRepository, userService);

        // Test
        historyService.addTransaction(transactionDto);

        // Assert
        verify(transactionRepository, times(1)).addTransaction(transactionDto);
    }

    @Test
    public void addTransactionWithoutUserCreatesUser() {
        // Setup
        String userShortName = faker.funnyName().name();
        TransactionDto transactionDto = mock(TransactionDto.class);
        UserDto userDto = mock(UserDto.class);
        when(transactionDto.getUser()).thenReturn(userDto);
        when(userDto.getUserId()).thenReturn(null);
        when(userDto.getShortName()).thenReturn(userShortName);

        // Test
        HistoryService historyService = new HistoryService(transactionRepository, userService);
        historyService.addTransaction(transactionDto);

        // Assert
        verify(transactionRepository, times(1)).addTransaction(transactionDto);
        verify(userService, times(1)).getOrCreateUserByShortName(userShortName);
    }
}