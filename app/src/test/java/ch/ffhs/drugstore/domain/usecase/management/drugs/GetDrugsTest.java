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
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugDto;
import util.TestUtil;

public class GetDrugsTest {
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
    public void executeThrowsNullPointerFor() {
        // Arrange
        MutableLiveData<List<DrugDto>> liveDataDrugList = new MutableLiveData<>();
        List<DrugDto> drugList = new ArrayList<>();
        drugList.add(TestUtil.createDrugDto(1));
        drugList.add(TestUtil.createDrugDto(2));
        liveDataDrugList.postValue(drugList);

        when(drugManagementService.getAllDrugs()).thenReturn(liveDataDrugList);

        // Act
        GetDrugs getDrugs = new GetDrugs(drugManagementService);
        LiveData<List<DrugDto>> actualDrugs = getDrugs.execute(null);

        // Verify
        verify(drugManagementService, times(1)).getAllDrugs();
        assertEquals(2, actualDrugs.getValue().size());
    }
}