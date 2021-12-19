package ch.ffhs.drugstore.domain.service;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.junit.Test;

import java.util.List;

import ch.ffhs.drugstore.data.repository.SignatureDrugRepository;
import ch.ffhs.drugstore.data.repository.SignatureRepository;
import ch.ffhs.drugstore.shared.dto.management.signature.SignatureDrugDto;
import ch.ffhs.drugstore.shared.dto.management.signature.SignatureDto;
import util.TestUtil;

/**
 * Test-class for SignatureService class
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class SignatureServiceTest {

    @Test
    public void getSignatures() {
        // Setup
        LiveData<List<SignatureDto>> signature =
                new MutableLiveData<>();
        SignatureRepository signatureRepository = mock(SignatureRepository.class);
        SignatureDrugRepository signatureDrugRepository = mock(SignatureDrugRepository.class);
        UserService userService = mock(UserService.class);
        when(signatureRepository.getSignatures()).thenReturn(signature);

        SignatureService signatureService = new SignatureService(signatureRepository,
                signatureDrugRepository, userService);

        // Test
        LiveData<List<SignatureDto>> result =
                signatureService.getSignatures();

        // Assert
        verify(signatureRepository, times(1)).getSignatures();
        verify(signatureRepository, times(0)).getSignatureBySignatureId(anyInt());
    }

    @Test void getSignatureById() {
        // Setup
        int signatureId = 1;
        List<SignatureDrugDto> signatureDrugs = TestUtil.createObjectListWithSupplier(2,
                TestUtil::createSignatureDrugDto);
        LiveData<List<SignatureDrugDto>> signatureDrugsLiveData = new MutableLiveData<>(signatureDrugs);
        SignatureRepository signatureRepository = mock(SignatureRepository.class);
        SignatureDrugRepository signatureDrugRepository = mock(SignatureDrugRepository.class);
        UserService userService = mock(UserService.class);
        when(signatureDrugRepository.getSignatureDrugsBySignatureId(anyInt())).thenReturn(signatureDrugsLiveData);

        SignatureService signatureService = new SignatureService(signatureRepository,
                signatureDrugRepository, userService);

        // Test
        LiveData<List<SignatureDrugDto>> result = signatureService.getSignatureDrugsBySignatureId(signatureId);

        // Assert
        verify(signatureRepository, times(0)).getSignatures();
        verify(signatureRepository, times(1)).getSignatureBySignatureId(anyInt());
    }
}