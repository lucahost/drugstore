package ch.ffhs.drugstore.domain.service;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.data.relation.SignatureWithUserAndSignatureDrugsAndDrugs;
import ch.ffhs.drugstore.data.repository.SignatureRepository;
import ch.ffhs.drugstore.data.repository.UserRepository;
import ch.ffhs.drugstore.shared.dto.management.signature.CreateSignatureDto;
import ch.ffhs.drugstore.shared.dto.management.signature.SignatureDto;
import ch.ffhs.drugstore.shared.dto.management.user.UserDto;

public class SignatureService {
    private final SignatureRepository signatureRepository;
    private final UserRepository userRepository;

    @Inject
    public SignatureService(SignatureRepository signatureRepository,
            UserRepository userRepository) {
        this.signatureRepository = signatureRepository;
        this.userRepository = userRepository;
    }

    public LiveData<List<SignatureWithUserAndSignatureDrugsAndDrugs>> getSignatures() {
        return signatureRepository.getSignatures();
    }

    public void createSignature(CreateSignatureDto createSignatureDto) {
        String userShortname = createSignatureDto.getUserShortName();
        UserDto user = userRepository.getOrCreateUserByShortName(userShortname);

        SignatureDto signatureDto = new SignatureDto(0, user, createSignatureDto.getCreatedAt());

        signatureRepository.createSignatureFrom(signatureDto,
                createSignatureDto.getSignatureDrugs());
    }
}
