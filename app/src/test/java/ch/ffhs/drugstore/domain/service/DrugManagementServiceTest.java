package ch.ffhs.drugstore.domain.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.when;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import ch.ffhs.drugstore.data.repository.DrugRepository;
import ch.ffhs.drugstore.data.repository.DrugTypeRepository;
import ch.ffhs.drugstore.data.repository.SubstanceRepository;
import ch.ffhs.drugstore.data.repository.UnitRepository;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugDto;
import util.TestUtil;

/**
 * Test-class for DrugManagementService class
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class DrugManagementServiceTest {
    @Mock
    private DrugRepository drugRepository;
    @Mock
    DrugTypeRepository drugTypeRepository;
    @Mock
    SubstanceRepository substanceRepository;
    @Mock
    UnitRepository unitRepository;

    DrugManagementService drugManagementService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);

        drugManagementService = new DrugManagementService(drugRepository,
                drugTypeRepository, substanceRepository, unitRepository);
    }

    @After
    public void tearDown() throws Exception {
        drugRepository = null;
    }

    @Test
    public void testGetAllDrugs() {
        // Arrange
        LiveData<List<DrugDto>> drugs = new MutableLiveData<>();
        when(drugRepository.getAllDrugs()).thenReturn(drugs);

        // Act
        LiveData<List<DrugDto>> actualDrugs =  drugManagementService.getAllDrugs();

        // Verify
        assertEquals(drugs, actualDrugs);
        verify(drugRepository, times(1)).getAllDrugs();
    }

    @Test
    public void getAllDrugTypes() {
    }

    @Test
    public void getAllUnits() {
    }

    @Test
    public void getAllSubstances() {
    }

    @Test
    public void testGetDrugById() {
        // Arrange
        int drugId = 1;
        DrugDto drugDto = TestUtil.createDrugDto(drugId);
        when(drugRepository.getDrugById(drugId)).thenReturn(drugDto);

        // Act
        drugManagementService.getDrugById(drugId);

        // Verify
        verify(drugRepository, times(1)).getDrugById(drugId);
        verify(drugRepository, times(0)).getAllDrugs();
    }

    @Test
    public void editDrug() {
    }

    @Test
    public void createDrug() {
    }

    @Test
    public void updateDrugAmount() {
    }

    @Test
    public void deleteDrug() {
    }
}