package ch.ffhs.drugstore.domain.service;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.data.relation.SignatureWithUserAndSignatureDrugsAndDrugs;
import ch.ffhs.drugstore.data.repository.SignatureDrugRepository;
import ch.ffhs.drugstore.data.repository.SignatureRepository;
import ch.ffhs.drugstore.data.repository.UserRepository;
import ch.ffhs.drugstore.shared.dto.management.signature.CreateSignatureDto;
import ch.ffhs.drugstore.shared.dto.management.signature.SignatureDrugDto;
import ch.ffhs.drugstore.shared.dto.management.signature.SignatureDto;
import ch.ffhs.drugstore.shared.dto.management.user.UserDto;

/**
 * This class represents a service to return a signature
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class SignatureService {
    private final SignatureRepository signatureRepository;
    private final UserRepository userRepository;
    private final SignatureDrugRepository signatureDrugRepository;

    @Inject
    public SignatureService(SignatureRepository signatureRepository, SignatureDrugRepository signatureDrugRepository,
            UserRepository userRepository) {
        this.signatureRepository = signatureRepository;
        this.signatureDrugRepository = signatureDrugRepository;
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

    public LiveData<List<SignatureDrugDto>> getSignatureDrugsBySignatureId(int signatureId) {
        return signatureDrugRepository.getSignatureDrugsBySignatureId(signatureId);
    }
}
