package ch.ffhs.drugstore.domain.usecase.management.drugs;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ch.ffhs.drugstore.domain.service.DrugManagementService;
import ch.ffhs.drugstore.shared.dto.management.drugs.EditDrugDto;
import ch.ffhs.drugstore.shared.exceptions.DrugstoreException;
import util.TestUtil;

public class EditDrugTest {
    private DrugManagementService drugManagementService;

    @Before
    public void setUp() {
        drugManagementService = mock(DrugManagementService.class);
    }

    @After
    public void tearDown() {
        drugManagementService = null;
    }

    @Test
    public void executeEditDrugUseCaseSuccessfully() throws DrugstoreException {
        // Arrange
        EditDrugDto editDrugDto = TestUtil.createEditDrugDto(1);

        // Act
        EditDrug editDrug = new EditDrug(drugManagementService);
        editDrug.execute(editDrugDto);

        // Verify
        verify(drugManagementService, times(1)).editDrug(editDrugDto);
    }
}