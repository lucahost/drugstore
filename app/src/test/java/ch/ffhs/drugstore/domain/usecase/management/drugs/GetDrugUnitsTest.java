package ch.ffhs.drugstore.domain.usecase.management.drugs;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import ch.ffhs.drugstore.domain.service.DrugManagementService;
import ch.ffhs.drugstore.shared.dto.management.drugs.UnitDto;
import util.TestUtil;

public class GetDrugUnitsTest {
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

    @Test
    public void executeGetDrugUnitsUseCaseSuccessfully() {
        // Arrange
        MutableLiveData<List<UnitDto>> liveDataUnitList = new MutableLiveData<>();
        List<UnitDto> drugUnitList = new ArrayList<>();
        drugUnitList.add(TestUtil.createDrugUnitDto(1));
        drugUnitList.add(TestUtil.createDrugUnitDto(2));
        drugUnitList.add(TestUtil.createDrugUnitDto(3));
        drugUnitList.add(TestUtil.createDrugUnitDto(4));
        drugUnitList.add(TestUtil.createDrugUnitDto(5));
        liveDataUnitList.postValue(drugUnitList);

        when(drugManagementService.getAllUnits()).thenReturn(liveDataUnitList);

        // Act
        GetDrugUnits getDrugUnits = new GetDrugUnits(drugManagementService);
        LiveData<List<UnitDto>> actualDrugUnits = getDrugUnits.execute(null);

        // Verify
        verify(drugManagementService, times(1)).getAllUnits();
        assertEquals(5, actualDrugUnits.getValue().size());
    }
}