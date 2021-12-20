package ch.ffhs.drugstore.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

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

/**
 * This class abstracts the data layer methods for Signature data
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class SignatureRepository {
    private final SignatureDao signatureDao;
    private final SignatureDrugDao signatureDrugDao;
    private final LiveData<List<SignatureWithUserAndSignatureDrugsAndDrugs>> allSignaturesWithDrugs;
    private final DrugstoreMapper mapper;

    @Inject
    public SignatureRepository(SignatureDao signatureDao, SignatureDrugDao signatureDrugDao) {
        this.mapper = DrugstoreMapper.INSTANCE;
        this.signatureDao = signatureDao;
        this.signatureDrugDao = signatureDrugDao;
        allSignaturesWithDrugs = signatureDao.getAllSignatures();
    }

    public LiveData<List<SignatureDto>> getSignatures() {
        return Transformations.map(allSignaturesWithDrugs,
                mapper::signatureListToSignatureDrugDtoList);
    }

    public SignatureDto getSignatureBySignatureId(int signatureId) {
        return mapper.signatureToSignatureDto(signatureDao.getSignatureBySignatureId(signatureId));
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    public void insert(Signature signature) {
        signatureDao.insert(signature);
    }

    public void update(Signature signature) {
        signatureDao.update(signature);
    }

    public void delete(Signature signature) {
        signatureDao.delete(signature);
    }

    public void deleteAll() {
        signatureDao.deleteAll();
    }

    /**
     * create Signature from list
     *
     * @param signatureDto
     * @param signatureDrugDtoList
     */
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