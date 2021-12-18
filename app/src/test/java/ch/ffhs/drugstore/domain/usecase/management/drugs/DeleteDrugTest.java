package ch.ffhs.drugstore.domain.usecase.management.drugs;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ch.ffhs.drugstore.domain.service.DrugManagementService;

public class DeleteDrugTest {
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
    public void executeDeleteDrugUseCaseSuccessfully() {
        // Arrange
        int drugId = 1;
        // Act
        DeleteDrug deleteDrug = new DeleteDrug(drugManagementService);
        deleteDrug.execute(drugId);

        // Verify
        verify(drugManagementService, times(1)).deleteDrug(drugId);
    }
}