package ch.ffhs.drugstore.domain.service;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.data.relation.SignatureWithUserAndSignatureDrugsAndDrugs;
import ch.ffhs.drugstore.data.repository.SignatureRepository;

public class SignatureService {
    @Inject
    SignatureRepository signatureRepository;

    @Inject
    public SignatureService() {
        // TODO document why this constructor is empty
    }

    public LiveData<List<SignatureWithUserAndSignatureDrugsAndDrugs>> getSignatures() {
        return signatureRepository.getSignatures();
    }
}
