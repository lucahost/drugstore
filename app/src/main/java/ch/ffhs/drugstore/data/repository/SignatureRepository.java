package ch.ffhs.drugstore.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.data.dao.SignatureDao;
import ch.ffhs.drugstore.data.database.DrugstoreDatabase;
import ch.ffhs.drugstore.data.dto.SignatureDto;
import ch.ffhs.drugstore.data.entity.Signature;

public class SignatureRepository {
    private final SignatureDao signatureDao;
    private final LiveData<List<SignatureDto>> allSignatures;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    @Inject
    public SignatureRepository(Application application) {
        DrugstoreDatabase db = DrugstoreDatabase.getDatabase(application);
        signatureDao = db.signatureDao();
        allSignatures = signatureDao.getAllSignatures();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<SignatureDto>> getAllSignatures() {
        return allSignatures;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    public void insert(Signature signature) {
        DrugstoreDatabase.databaseWriteExecutor.execute(() -> signatureDao.insert(signature));
    }

    public void update(Signature signature) {
        DrugstoreDatabase.databaseWriteExecutor.execute(() -> signatureDao.update(signature));
    }

    public void delete(Signature signature) {
        DrugstoreDatabase.databaseWriteExecutor.execute(() -> signatureDao.delete(signature));
    }

    public void deleteAll() {
        DrugstoreDatabase.databaseWriteExecutor.execute(signatureDao::deleteAll);
    }
}