package ch.ffhs.drugstore.domain.service;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.data.relation.SignatureWithUserAndSignatureDrugsAndDrugs;
import ch.ffhs.drugstore.data.repository.SignatureRepository;

public class SignatureService {
    private final SignatureRepository signatureRepository;

    @Inject
    public SignatureService(SignatureRepository signatureRepository) {
        this.signatureRepository = signatureRepository;
    }

    public LiveData<List<SignatureWithUserAndSignatureDrugsAndDrugs>> getSignatures() {
        return signatureRepository.getSignatures();
    }
}
