package ch.ffhs.drugstore.domain.service;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.data.relation.SignatureWithUserAndSignatureDrugsAndDrugs;
import ch.ffhs.drugstore.data.repository.SignatureRepository;
/**
 * This class represents a service to return a signature
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class SignatureService {
    private final SignatureRepository signatureRepository;

    /**
     *
     * @param signatureRepository
     */
    @Inject
    public SignatureService(SignatureRepository signatureRepository) {
        this.signatureRepository = signatureRepository;
    }

    /**
     *
     * @return
     */
    public LiveData<List<SignatureWithUserAndSignatureDrugsAndDrugs>> getSignatures() {
        return signatureRepository.getSignatures();
    }
}
