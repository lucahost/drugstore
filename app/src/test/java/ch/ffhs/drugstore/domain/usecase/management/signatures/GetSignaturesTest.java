package ch.ffhs.drugstore.domain.usecase.management.signatures;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import ch.ffhs.drugstore.domain.service.SignatureService;
import ch.ffhs.drugstore.shared.dto.management.signature.SignatureDto;
import util.TestUtil;

public class GetSignaturesTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private SignatureService signatureService;

    @Before
    public void setUp() {
        signatureService = mock(SignatureService.class);
    }

    @After
    public void tearDown() {
        signatureService = null;
    }


    @Test
    public void executeGetSignatureUseCaseSuccessfully() {
        // Arrange
        MutableLiveData<List<SignatureDto>> liveDataSignatureList = new MutableLiveData<>();
        List<SignatureDto> signatureDtoList = new ArrayList<>();
        signatureDtoList.add(TestUtil.createSignatureDto(1));
        signatureDtoList.add(TestUtil.createSignatureDto(2));
        liveDataSignatureList.postValue(signatureDtoList);
        when(signatureService.getSignatures()).thenReturn(liveDataSignatureList);

        // Act
        GetSignatures getSignatures = new GetSignatures(signatureService);
        LiveData<List<SignatureDto>> actualSignatures = getSignatures.execute(null);

        // Verify
        verify(signatureService, times(0)).createSignature(any());
        verify(signatureService, times(1)).getSignatures();
        assertEquals(liveDataSignatureList, actualSignatures);
    }
}