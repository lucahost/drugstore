package ch.ffhs.drugstore.domain.usecase.management.inventory;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import ch.ffhs.drugstore.domain.service.SignatureService;
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