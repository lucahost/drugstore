package ch.ffhs.drugstore.domain.usecase.management.inventory;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.whenNew;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import ch.ffhs.drugstore.domain.service.DrugManagementService;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugDto;
import ch.ffhs.drugstore.shared.dto.management.signature.SignatureDrugDto;
import ch.ffhs.drugstore.shared.exceptions.DrugNotFoundException;
import ch.ffhs.drugstore.shared.exceptions.DrugstoreException;
import util.TestUtil;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ToggleInventoryItem.class})
public class ToggleInventoryItemTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private DrugManagementService drugManagementService;

    @Before
    public void setUp() {
        drugManagementService = mock(DrugManagementService.class);
    }

    @After
    public void tearDown() {
        drugManagementService = null;
    }

    @Test(expected = DrugNotFoundException.class)
    public void executeToggleInventoryUseCaseThrowsDrugNotFound() throws DrugstoreException {
        // Arrange
        Integer drugId = 1;
        when(drugManagementService.getDrugById(drugId)).thenReturn(null);
        // Act
        ToggleInventoryItem toggleInventoryItem = new ToggleInventoryItem(drugManagementService);
        toggleInventoryItem.execute(drugId);
    }

    @Test
    public void executeToggleInventoryUseCaseSuccessFully() throws Exception {
        // Arrange
        Integer drugId = 2;
        DrugDto drugDto = TestUtil.createDrugDto(drugId);
        SignatureDrugDto signatureDrugDto = TestUtil.createSignatureDrugDto();

        when(drugManagementService.getDrugById(drugId)).thenReturn(drugDto);
        whenNew(SignatureDrugDto.class).withAnyArguments().thenReturn(signatureDrugDto);

        // Act
        ToggleInventoryItem toggleInventoryItem = new ToggleInventoryItem(drugManagementService);
        SignatureDrugDto actualSignatureDrug = toggleInventoryItem.execute(drugId);

        // Verify
        assertEquals(signatureDrugDto, actualSignatureDrug);
    }
}