package ch.ffhs.drugstore.domain.usecase.management.inventory;

import static org.junit.Assert.*;
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

import ch.ffhs.drugstore.domain.service.InventoryService;
import ch.ffhs.drugstore.domain.service.SignatureService;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugDto;
import ch.ffhs.drugstore.shared.dto.management.signature.CreateSignatureDto;
import util.TestUtil;

public class SignInventoryTest {
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
    public void executeSignInventoryUseCaseSuccessfully() {
        // Arrange
        CreateSignatureDto createSignatureDto = TestUtil.createCreateSignatureDto();

        // Act
        SignInventory signInventory = new SignInventory(signatureService);
        signInventory.execute(createSignatureDto);

        // Verify
        verify(signatureService, times(1)).createSignature(createSignatureDto);
        verify(signatureService, times(0)).getSignatures();
    }
}