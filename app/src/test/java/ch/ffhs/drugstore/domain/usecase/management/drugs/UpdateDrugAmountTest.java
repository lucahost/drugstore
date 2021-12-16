package ch.ffhs.drugstore.domain.usecase.management.drugs;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import ch.ffhs.drugstore.domain.service.DrugManagementService;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugDto;
import ch.ffhs.drugstore.shared.dto.management.drugs.UpdateDrugAmountDto;
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

        DrugDto fakeDrug = TestUtil.createDrugDto(0);
        fakeDrug.setStockAmount(0);
        when(drugManagementService.getDrugById(0)).thenReturn(fakeDrug);

        // Act
        UpdateDrugAmount updateDrugAmount = new UpdateDrugAmount(drugManagementService);
        updateDrugAmount.execute(updateDrugAmountDto);
    }
}