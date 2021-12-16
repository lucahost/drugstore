package ch.ffhs.drugstore.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.data.dao.SignatureDao;
import ch.ffhs.drugstore.data.dao.SignatureDrugDao;
import ch.ffhs.drugstore.data.database.DrugstoreDatabase;
import ch.ffhs.drugstore.data.entity.Signature;
import ch.ffhs.drugstore.data.entity.SignatureDrug;
import ch.ffhs.drugstore.data.relation.SignatureWithUserAndSignatureDrugsAndDrugs;
import ch.ffhs.drugstore.shared.dto.management.signature.SignatureDrugDto;
import ch.ffhs.drugstore.shared.dto.management.signature.SignatureDto;
import ch.ffhs.drugstore.shared.mappers.DrugstoreMapper;

public class SignatureRepository {
    private final SignatureDao signatureDao;
    private final SignatureDrugDao signatureDrugDao;
    private final LiveData<List<SignatureWithUserAndSignatureDrugsAndDrugs>> allSignaturesWithDrugs;
    private final DrugstoreMapper mapper;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    @Inject
    public SignatureRepository(Application application) {
        DrugstoreDatabase db = DrugstoreDatabase.getDatabase(application);
        this.mapper = DrugstoreMapper.INSTANCE;
        signatureDao = db.signatureDao();
        signatureDrugDao = db.signatureDrugDao();
        allSignaturesWithDrugs = signatureDao.getAllSignatures();
    }

    public LiveData<List<SignatureWithUserAndSignatureDrugsAndDrugs>> getSignatures() {
        return allSignaturesWithDrugs;
    }

    public SignatureDto getSignatureBySignatureId(int signatureId) {
        return mapper.signatureToSignatureDto(signatureDao.getSignatureBySignatureId(signatureId));
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

    public void createSignatureFrom(SignatureDto signatureDto,
            List<SignatureDrugDto> signatureDrugDtoList) {
        List<SignatureDrug> signatureDrugs = mapper.signatureDrugDtoListToSignatureDrugList(
                signatureDrugDtoList);

        Signature signature = mapper.signatureDtoToSignature(signatureDto);

        long newSignatureId = signatureDao.insert(signature);
        signatureDrugs.stream().forEach(s -> s.setSignatureId((int) newSignatureId));
        
        signatureDrugDao.insert(signatureDrugs.toArray(new SignatureDrug[0]));
    }
}