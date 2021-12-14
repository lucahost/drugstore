package ch.ffhs.drugstore.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.data.dao.TransactionDao;
import ch.ffhs.drugstore.data.database.DrugstoreDatabase;
import ch.ffhs.drugstore.data.entity.Transaction;
import ch.ffhs.drugstore.shared.dto.management.history.TransactionDto;

public class TransactionRepository {
    private final TransactionDao transactionDao;
    private final LiveData<List<TransactionDto>> allTransactions;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    @Inject
    public TransactionRepository(Application application) {
        DrugstoreDatabase db = DrugstoreDatabase.getDatabase(application);
        transactionDao = db.transactionDao();
        allTransactions = transactionDao.getAllTransactions();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<TransactionDto>> getAllTransactions() {
        return allTransactions;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    public void insert(Transaction transaction) {
        DrugstoreDatabase.databaseWriteExecutor.execute(() -> transactionDao.insert(transaction));
    }

    public void update(Transaction transaction) {
        DrugstoreDatabase.databaseWriteExecutor.execute(() -> transactionDao.update(transaction));
    }

    public void delete(Transaction transaction) {
        DrugstoreDatabase.databaseWriteExecutor.execute(() -> transactionDao.delete(transaction));
    }

    public void deleteAll() {
        DrugstoreDatabase.databaseWriteExecutor.execute(transactionDao::deleteAll);
    }
}
