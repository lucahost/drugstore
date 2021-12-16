package ch.ffhs.drugstore.domain.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.junit.Test;

import java.util.List;

import ch.ffhs.drugstore.data.relation.SignatureWithUserAndSignatureDrugsAndDrugs;
import ch.ffhs.drugstore.data.repository.SignatureDrugRepository;
import ch.ffhs.drugstore.data.repository.SignatureRepository;
import ch.ffhs.drugstore.data.repository.UserRepository;

/**
 * Test-class for SignatureService class
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class SignatureServiceTest {

    /**
     *
     */
    @Test
    public void getSignatures() {
        // Setup
        LiveData<List<SignatureWithUserAndSignatureDrugsAndDrugs>> signature =
                new MutableLiveData<>();
        SignatureRepository signatureRepository = mock(SignatureRepository.class);
        SignatureDrugRepository signatureDrugRepository = mock(SignatureDrugRepository.class);
        UserRepository userRepository = mock(UserRepository.class);
        when(signatureRepository.getSignatures()).thenReturn(signature);

        SignatureService signatureService = new SignatureService(signatureRepository,
                signatureDrugRepository, userRepository);

        // Test
        LiveData<List<SignatureWithUserAndSignatureDrugsAndDrugs>> result =
                signatureService.getSignatures();

        // Assert
        verify(signatureRepository, times(1)).getSignatures();
    }
}