package ch.ffhs.drugstore.domain.usecase.management.drugs;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ch.ffhs.drugstore.domain.service.DrugManagementService;
import ch.ffhs.drugstore.shared.dto.management.drugs.CreateDrugDto;
import util.TestUtil;

public class CreateDrugTest {
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
    public void executeCreateDrugUseCaseSuccessfully() throws Exception {
        // Arrange
        CreateDrugDto createDrugDto = TestUtil.createCreateDrugDto();

        // Act
        CreateDrug createDrug = new CreateDrug(drugManagementService);
        createDrug.execute(createDrugDto);

        // Verify
        verify(drugManagementService, times(1)).createDrug(createDrugDto);
    }
}