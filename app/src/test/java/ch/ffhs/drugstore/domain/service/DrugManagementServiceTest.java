package ch.ffhs.drugstore.domain.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
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
import ch.ffhs.drugstore.shared.dto.management.drugs.CreateDrugDto;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugDto;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugTypeDto;
import ch.ffhs.drugstore.shared.dto.management.drugs.EditDrugDto;
import ch.ffhs.drugstore.shared.dto.management.drugs.SubstanceDto;
import ch.ffhs.drugstore.shared.dto.management.drugs.UnitDto;
import ch.ffhs.drugstore.shared.dto.management.drugs.UpdateDrugAmountDto;
import ch.ffhs.drugstore.shared.exceptions.DrugNotFoundException;
import ch.ffhs.drugstore.shared.exceptions.DrugstoreException;
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
        LiveData<List<DrugDto>> actualDrugs = drugManagementService.getAllDrugs();

        // Verify
        assertEquals(drugs, actualDrugs);
        verify(drugRepository, times(1)).getAllDrugs();
    }

    @Test
    public void getAllDrugTypes() {
        // Arrange
        LiveData<List<DrugTypeDto>> drugTypes = new MutableLiveData<>();
        when(drugTypeRepository.getAllDrugTypes()).thenReturn(drugTypes);

        // Act
        LiveData<List<DrugTypeDto>> actualDrugTypes = drugManagementService.getAllDrugTypes();

        // Verify
        assertEquals(drugTypes, actualDrugTypes);
        verify(drugTypeRepository, times(1)).getAllDrugTypes();
    }

    @Test
    public void getAllUnits() {
        // Arrange
        LiveData<List<UnitDto>> units = new MutableLiveData<>();
        when(unitRepository.getAllUnits()).thenReturn(units);

        // Act
        LiveData<List<UnitDto>> actualUnits = drugManagementService.getAllUnits();

        // Verify
        assertEquals(units, actualUnits);
        verify(unitRepository, times(1)).getAllUnits();
    }

    @Test
    public void getAllSubstances() {
        // Arrange
        LiveData<List<SubstanceDto>> substances = new MutableLiveData<>();
        when(substanceRepository.getAllSubstances()).thenReturn(substances);

        // Act
        LiveData<List<SubstanceDto>> actualSubstances = drugManagementService.getAllSubstances();

        // Verify
        assertEquals(substances, actualSubstances);
        verify(substanceRepository, times(1)).getAllSubstances();
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
    public void editDrug() throws DrugNotFoundException {
        // Arrange
        int drugId = 1;
        int substanceId = 1;
        EditDrugDto editDrugDto = TestUtil.createEditDrugDto(drugId);
        DrugDto drugDto = TestUtil.createDrugDto(drugId);
        SubstanceDto substanceDto = TestUtil.createSubstanceDto(substanceId);
        when(drugRepository.getDrugById(drugId)).thenReturn(drugDto);
        when(substanceRepository.getOrCreateSubstanceByTitle(
                editDrugDto.getSubstance())).thenReturn(substanceDto);

        // Act
        drugManagementService.editDrug(editDrugDto);

        // Verify
        verify(drugRepository, times(1)).getDrugById(drugId);
        verify(drugRepository, times(1)).editDrug(any());
    }

    @Test(expected = DrugNotFoundException.class)
    public void editNonExistingDrug() throws DrugNotFoundException {
        // Arrange
        int drugId = 1;
        EditDrugDto editDrugDto = TestUtil.createEditDrugDto(drugId);
        when(drugRepository.getDrugById(drugId)).thenReturn(null);

        // Act
        drugManagementService.editDrug(editDrugDto);

        // Verify
        verify(drugRepository, times(1)).getDrugById(drugId);
    }

    @Test
    public void createDrug() {
        // Arrange
        int substanceId = 1;
        CreateDrugDto createDrugDto = TestUtil.createCreateDrugDto();
        SubstanceDto substanceDto = TestUtil.createSubstanceDto(substanceId);
        when(substanceRepository.getOrCreateSubstanceByTitle(createDrugDto.getSubstance())).thenReturn(substanceDto);

        // Act
        drugManagementService.createDrug(createDrugDto);

        // Verify
        verify(drugRepository, times(1)).createDrug(any());
    }

    @Test(expected = DrugNotFoundException.class)
    public void updateNotExistingDrugDrugAmount() throws DrugstoreException {
        // Arrange
        int drugId = 1;
        UpdateDrugAmountDto updateDrugAmountDto = TestUtil.createUpdateDrugAmountDto(drugId);
        when(drugRepository.getDrugById(drugId)).thenReturn(null);

        // Act
        drugManagementService.updateDrugAmount(updateDrugAmountDto);
    }

    @Test
    public void updateDrugAmount() throws DrugstoreException {
        // Arrange
        int drugId = 1;
        UpdateDrugAmountDto updateDrugAmountDto = TestUtil.createUpdateDrugAmountDto(drugId);
        DrugDto drugDto = TestUtil.createDrugDto(drugId);
        when(drugRepository.getDrugById(drugId)).thenReturn(drugDto);

        // Act
        drugManagementService.updateDrugAmount(updateDrugAmountDto);

        // Verify
        verify(drugRepository, times(1)).updateDrugAmount(drugId, updateDrugAmountDto.getAmount());
    }

    @Test
    public void deleteDrug() {
        // Arrange
        int drugId = 1;

        // Act
        drugManagementService.deleteDrug(drugId);

        // Verify
        verify(drugRepository, times(1)).deleteDrugById(drugId);
    }
}