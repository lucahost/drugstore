package ch.ffhs.drugstore.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.data.dao.SignatureDrugDao;
import ch.ffhs.drugstore.data.database.DrugstoreDatabase;
import ch.ffhs.drugstore.shared.dto.management.signature.SignatureDrugDto;
import ch.ffhs.drugstore.shared.mappers.DrugstoreMapper;

public class SignatureDrugRepository {
    private final SignatureDrugDao signatureDrugDao;
    private final DrugstoreMapper mapper;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    @Inject
    public SignatureDrugRepository(Application application) {
        DrugstoreDatabase db = DrugstoreDatabase.getDatabase(application);
        this.mapper = DrugstoreMapper.INSTANCE;
        signatureDrugDao = db.signatureDrugDao();
    }

    public LiveData<List<SignatureDrugDto>> getSignatureDrugsBySignatureId(int signatureId) {
        return Transformations.map(
                signatureDrugDao.getSignatureDrugsWithDrugBySignatureId(signatureId),
                mapper::signatureDrugListToSignatureDrugDtoList);
    }
}