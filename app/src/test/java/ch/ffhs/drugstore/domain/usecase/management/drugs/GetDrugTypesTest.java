package ch.ffhs.drugstore.domain.usecase.management.drugs;

import static org.junit.Assert.*;
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
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugDto;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugTypeDto;
import util.TestUtil;

public class GetDrugTypesTest {
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
    public void executeGetDrugTypesUseCaseSuccessfully() {
        // Arrange
        MutableLiveData<List<DrugTypeDto>> liveDataDrugTypeList = new MutableLiveData<>();
        List<DrugTypeDto> drugTypeList = new ArrayList<>();
        drugTypeList.add(TestUtil.createDrugTypeDto(1));
        drugTypeList.add(TestUtil.createDrugTypeDto(2));
        drugTypeList.add(TestUtil.createDrugTypeDto(3));
        liveDataDrugTypeList.postValue(drugTypeList);

        when(drugManagementService.getAllDrugTypes()).thenReturn(liveDataDrugTypeList);

        // Act
        GetDrugTypes getDrugTypes = new GetDrugTypes(drugManagementService);
        LiveData<List<DrugTypeDto>> actualDrugTypes = getDrugTypes.execute(null);

        // Verify
        verify(drugManagementService, times(1)).getAllDrugTypes();
        assertEquals(3, actualDrugTypes.getValue().size());
    }
}