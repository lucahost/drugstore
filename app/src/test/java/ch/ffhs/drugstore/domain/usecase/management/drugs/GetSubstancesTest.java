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
import ch.ffhs.drugstore.shared.dto.management.drugs.SubstanceDto;
import util.TestUtil;

public class GetSubstancesTest {
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
    public void executeGetSubstancesUseCaseSuccessfully() {
        // Arrange
        MutableLiveData<List<SubstanceDto>> liveDataSubstanceList = new MutableLiveData<>();
        List<SubstanceDto> substanceList = new ArrayList<>();
        substanceList.add(TestUtil.createSubstanceDto(1));
        substanceList.add(TestUtil.createSubstanceDto(2));
        substanceList.add(TestUtil.createSubstanceDto(3));
        substanceList.add(TestUtil.createSubstanceDto(4));
        substanceList.add(TestUtil.createSubstanceDto(5));
        substanceList.add(TestUtil.createSubstanceDto(6));
        substanceList.add(TestUtil.createSubstanceDto(7));
        liveDataSubstanceList.postValue(substanceList);

        when(drugManagementService.getAllSubstances()).thenReturn(liveDataSubstanceList);

        // Act
        GetSubstances getSubstances = new GetSubstances(drugManagementService);
        LiveData<List<SubstanceDto>> actualSubstances = getSubstances.execute(null);

        // Verify
        verify(drugManagementService, times(1)).getAllSubstances();
        assertEquals(7, actualSubstances.getValue().size());
    }
}