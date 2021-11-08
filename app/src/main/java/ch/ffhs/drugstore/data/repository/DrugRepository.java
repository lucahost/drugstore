package ch.ffhs.drugstore.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import ch.ffhs.drugstore.data.dao.DrugDao;
import ch.ffhs.drugstore.data.database.DrugstoreDatabase;
import ch.ffhs.drugstore.data.entity.Drug;

public class DrugRepository {
    private final DrugDao drugDao;
    private final LiveData<List<Drug>> allDrugs;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    @Inject
    public DrugRepository(Application application) {
        DrugstoreDatabase db = DrugstoreDatabase.getDatabase(application);
        drugDao = db.drugDao();
        allDrugs = drugDao.getAll();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<Drug>> getAllTodos() {
        return allDrugs;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    public void insert(Drug drug) {
        DrugstoreDatabase.databaseWriteExecutor.execute(() -> drugDao.insert(drug));
    }

    public void update(Drug drug) {
        DrugstoreDatabase.databaseWriteExecutor.execute(() -> drugDao.update(drug));
    }

    public void delete(Drug drug) {
        DrugstoreDatabase.databaseWriteExecutor.execute(() -> drugDao.delete(drug));
    }

    public void deleteAll() {
        DrugstoreDatabase.databaseWriteExecutor.execute(drugDao::deleteAll);
    }
}
