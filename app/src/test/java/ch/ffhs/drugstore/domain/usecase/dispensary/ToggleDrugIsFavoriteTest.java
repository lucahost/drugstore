package ch.ffhs.drugstore.domain.usecase.dispensary;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ch.ffhs.drugstore.domain.service.DispensaryService;
import ch.ffhs.drugstore.shared.exceptions.DrugstoreException;

public class ToggleDrugIsFavoriteTest {
    @Mock
    DispensaryService dispensaryService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @After
    public void tearDown() throws Exception {
        dispensaryService = null;
    }

    @Test
    public void testExecuteToggleDrugIsFavorite() throws DrugstoreException {
        // Arrange
        int drugId = 1;

        // Act
        ToggleDrugIsFavorite toggleDrugIsFavorite = new ToggleDrugIsFavorite(dispensaryService);
        toggleDrugIsFavorite.execute(drugId);

        // Verify
        verify(dispensaryService, times(1)).toggleDrugIsFavorite(drugId);
    }
}