package ch.ffhs.drugstore.domain.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.when;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import ch.ffhs.drugstore.data.repository.DrugRepository;
import ch.ffhs.drugstore.presentation.dispensary.view.FilterState;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugDto;
import ch.ffhs.drugstore.shared.exceptions.DrugNotFoundException;
import ch.ffhs.drugstore.shared.exceptions.DrugstoreException;
import util.TestUtil;

/**
 * Test-class for DispensaryService class
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class DispensaryServiceTest {
    private DrugRepository drugRepository = null;

    @Before
    public void setUp() throws Exception {
        drugRepository = mock(DrugRepository.class);
    }

    @After
    public void tearDown() throws Exception {
        drugRepository = null;
    }

    @Test(expected = NullPointerException.class)
    public void testGetAllDrugsWithoutFilterThrowsException() {
        // Assert
        DispensaryService dispensaryService = new DispensaryService(drugRepository);

        // Act
        dispensaryService.getAllDrugs(null);
    }


    @Test
    public void testGetAllDrugsWithFilterWithoutFilters() {
        // Assert
        DispensaryService dispensaryService = new DispensaryService(drugRepository);

        FilterState filterState = new FilterState();
        LiveData<List<DrugDto>> drugs = new MutableLiveData<>();
        when(drugRepository.getOnStockDrugs(any(boolean.class), any(String.class))).thenReturn(
                drugs);

        // Act
        LiveData<List<DrugDto>> actualDrugs = dispensaryService.getAllDrugs(filterState);

        // Verify
        verify(drugRepository, times(1)).getOnStockDrugs(filterState.isFavorites(),
                filterState.getSearchFilter());
        verify(drugRepository, times(0)).getOnStockDrugs(filterState.isFavorites(),
                filterState.getFilters(), filterState.getSearchFilter());
        assertEquals(drugs, actualDrugs);
    }

    @Test
    public void testGetAllDrugsWithFilterWithFilters() {
        // Assert
        DispensaryService dispensaryService = new DispensaryService(drugRepository);

        FilterState filterState = new FilterState();
        filterState.toggleFilter(1);
        LiveData<List<DrugDto>> drugs = new MutableLiveData<>();
        when(drugRepository.getOnStockDrugs(any(boolean.class), any(),
                any(String.class))).thenReturn(
                drugs);

        // Act
        LiveData<List<DrugDto>> actualDrugs = dispensaryService.getAllDrugs(filterState);

        // Verify
        verify(drugRepository, times(0)).getOnStockDrugs(filterState.isFavorites(),
                filterState.getSearchFilter());
        verify(drugRepository, times(1)).getOnStockDrugs(filterState.isFavorites(),
                filterState.getFilters(), filterState.getSearchFilter());
        assertEquals(drugs, actualDrugs);
    }

    @Test(expected = DrugNotFoundException.class)
    public void updateDrugAmountThrowsIfDrugIsNotFound() throws DrugstoreException {
        // Assert
        DispensaryService dispensaryService = new DispensaryService(drugRepository);

        // Act
        dispensaryService.toggleDrugIsFavorite(1);

        // Verify
        verify(drugRepository, times(0)).toggleDrugIsFavorite(1);
    }

    @Test
    public void updateDrugAmount() throws DrugstoreException {
        // Assert
        DispensaryService dispensaryService = new DispensaryService(drugRepository);
        DrugDto fakeDrug = TestUtil.createDrugDto(1);
        when(drugRepository.getDrugById(1)).thenReturn(fakeDrug);

        // Act
        dispensaryService.toggleDrugIsFavorite(1);

        // Verify
        verify(drugRepository, times(0)).toggleDrugIsFavorite(1);
    }
}