package ch.ffhs.drugstore.domain.usecase.management.drugs;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ch.ffhs.drugstore.domain.service.DrugManagementService;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugDto;
import ch.ffhs.drugstore.shared.dto.management.drugs.UpdateDrugAmountDto;
import ch.ffhs.drugstore.shared.exceptions.DrugNotFoundException;
import ch.ffhs.drugstore.shared.exceptions.DrugstoreException;
import ch.ffhs.drugstore.shared.exceptions.InsufficientAmountException;
import util.TestUtil;

public class UpdateDrugAmountTest {
    private DrugManagementService drugManagementService;

    @Before
    public void setUp() {
        drugManagementService = mock(DrugManagementService.class);
    }

    @After
    public void tearDown() {
        drugManagementService = null;
    }

    @Test(expected = InsufficientAmountException.class)
    public void executeWithInsufficientAmountThrowsException() throws DrugstoreException {
        // Arrange
        UpdateDrugAmountDto updateDrugAmountDto = TestUtil.createUpdateDrugAmountDto(0);
        updateDrugAmountDto.setAmount(-10);

        DrugDto fakeDrug = TestUtil.createDrugDto(0);
        fakeDrug.setStockAmount(0);
        when(drugManagementService.getDrugById(0)).thenReturn(fakeDrug);

        // Act
        UpdateDrugAmount updateDrugAmount = new UpdateDrugAmount(drugManagementService);
        updateDrugAmount.execute(updateDrugAmountDto);
    }

    @Test(expected = DrugNotFoundException.class)
    public void executeWithWrongDrugThrowsDrugNotFoundException() throws DrugstoreException {
        // Arrange
        UpdateDrugAmountDto updateDrugAmountDto = TestUtil.createUpdateDrugAmountDto(1);
        updateDrugAmountDto.setAmount(-10);

        DrugDto fakeDrug = TestUtil.createDrugDto(1);
        fakeDrug.setStockAmount(0);
        when(drugManagementService.getDrugById(0)).thenReturn(fakeDrug);

        // Act
        UpdateDrugAmount updateDrugAmount = new UpdateDrugAmount(drugManagementService);
        updateDrugAmount.execute(updateDrugAmountDto);
    }

    @Test
    public void executeUpdateDrugAmountUseCaseSuccessfully() throws DrugstoreException {
        // Arrange
        UpdateDrugAmountDto updateDrugAmountDto = TestUtil.createUpdateDrugAmountDto(0);
        updateDrugAmountDto.setAmount(2);

        DrugDto fakeDrug = TestUtil.createDrugDto(0);
        fakeDrug.setStockAmount(0);
        when(drugManagementService.getDrugById(0)).thenReturn(fakeDrug);

        // Act
        UpdateDrugAmount updateDrugAmount = new UpdateDrugAmount(drugManagementService);
        updateDrugAmount.execute(updateDrugAmountDto);
    }
}