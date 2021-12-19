package ch.ffhs.drugstore.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.data.dao.SignatureDrugDao;
import ch.ffhs.drugstore.shared.dto.management.signature.SignatureDrugDto;
import ch.ffhs.drugstore.shared.mappers.DrugstoreMapper;

/**
 * This class abstracts the data layer methods for Drug Signature data
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class SignatureDrugRepository {
    private final SignatureDrugDao signatureDrugDao;
    private final DrugstoreMapper mapper;

    @Inject
    public SignatureDrugRepository(SignatureDrugDao signatureDrugDao) {
        this.mapper = DrugstoreMapper.INSTANCE;
        this.signatureDrugDao = signatureDrugDao;
    }

    /**
     * @param signatureId
     * @return Live Data List with Drug Signatures
     */
    public LiveData<List<SignatureDrugDto>> getSignatureDrugsBySignatureId(int signatureId) {
        return Transformations.map(
                signatureDrugDao.getSignatureDrugsWithDrugBySignatureId(signatureId),
                mapper::signatureDrugListToSignatureDrugDtoList);
    }
}