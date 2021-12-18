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
import ch.ffhs.drugstore.shared.dto.management.signature.SignatureDrugDto;
import util.TestUtil;

public class GetSignatureDrugTest {
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
    public void executeGetSignatureDrugUseCaseSuccessfully() {
        // Arrange
        Integer signatureId = 1;
        MutableLiveData<List<SignatureDrugDto>> liveDataSignatureDrugList = new MutableLiveData<>();
        List<SignatureDrugDto> signatureDrugDtoList = new ArrayList<>();
        signatureDrugDtoList.add(TestUtil.createSignatureDrugDto());
        signatureDrugDtoList.add(TestUtil.createSignatureDrugDto());
        liveDataSignatureDrugList.postValue(signatureDrugDtoList);
        when(signatureService.getSignatureDrugsBySignatureId(signatureId)).thenReturn(
                liveDataSignatureDrugList);

        // Act
        GetSignatureDrug getSignatureDrug = new GetSignatureDrug(signatureService);
        LiveData<List<SignatureDrugDto>> actualSignatureDrugs = getSignatureDrug.execute(
                signatureId);

        // Verify
        verify(signatureService, times(0)).createSignature(any());
        verify(signatureService, times(0)).getSignatures();
        verify(signatureService, times(1)).getSignatureDrugsBySignatureId(signatureId);
        assertEquals(liveDataSignatureDrugList, actualSignatureDrugs);
    }
}