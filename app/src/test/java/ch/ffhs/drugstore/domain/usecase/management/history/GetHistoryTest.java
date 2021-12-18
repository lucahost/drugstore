package ch.ffhs.drugstore.domain.usecase.management.history;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import ch.ffhs.drugstore.domain.service.HistoryService;
import ch.ffhs.drugstore.shared.dto.management.history.TransactionDto;
import util.TestUtil;

public class GetHistoryTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private HistoryService historyService;

    @Before
    public void setUp() {
        historyService = mock(HistoryService.class);
    }

    @After
    public void tearDown() {
        historyService = null;
    }

    @Test
    public void executeHistoryUseCaseSuccessfully() {
        // Arrange
        MutableLiveData<List<TransactionDto>> liveDataTransactionList = new MutableLiveData<>();
        List<TransactionDto> transactionList = new ArrayList<>();
        transactionList.add(TestUtil.createTransactionDto(1));
        transactionList.add(TestUtil.createTransactionDto(2));
        liveDataTransactionList.postValue(transactionList);

        when(historyService.getHistory()).thenReturn(liveDataTransactionList);

        // Act
        GetHistory getHistory = new GetHistory(historyService);
        LiveData<List<TransactionDto>> actualTransaction = getHistory.execute(null);

        // Verify
        verify(historyService, times(1)).getHistory();
        verify(historyService, times(0)).addTransaction(any());
        assertEquals(2, actualTransaction.getValue().size());
    }
}