package ch.ffhs.drugstore.domain.service;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.data.dto.SignatureDto;
import ch.ffhs.drugstore.data.dto.SignatureWithDrugs;
import ch.ffhs.drugstore.data.repository.SignatureRepository;

public class SignatureService {
    @Inject
    SignatureRepository signatureRepository;

    @Inject
    public SignatureService() {
        // TODO document why this constructor is empty
    }

    public LiveData<List<SignatureDto>> getAllSignatures() {
        return signatureRepository.getAllSignatures();
    }

    public LiveData<List<SignatureWithDrugs>> getSignatures() {
        return signatureRepository.getSignatures();
    }
}
