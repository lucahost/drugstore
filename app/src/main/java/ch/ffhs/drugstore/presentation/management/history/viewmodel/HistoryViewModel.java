package ch.ffhs.drugstore.presentation.management.history.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.domain.usecase.management.history.GetHistory;
import ch.ffhs.drugstore.shared.dto.management.history.TransactionDto;
import dagger.hilt.android.lifecycle.HiltViewModel;

/**
 * View model for the history.
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
@HiltViewModel
public class HistoryViewModel extends AndroidViewModel {
    private final GetHistory getHistory;
    private LiveData<List<TransactionDto>> transactions;

    /**
     * Constructs a {@link HistoryViewModel}
     *
     * @param application global application state
     * @param getHistory use case to get the history
     */
    @Inject
    public HistoryViewModel(Application application,
            GetHistory getHistory) {
        super(application);
        this.getHistory = getHistory;
    }

    /**
     * Get all transactions.
     *
     * @return transactions
     */
    public LiveData<List<TransactionDto>> getTransactions() {
        if (transactions == null) {
            transactions = getHistory.execute(null);
        }
        return transactions;
    }
}
