package ch.ffhs.drugstore.domain.usecase.dispensary;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
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

import ch.ffhs.drugstore.domain.service.DispensaryService;
import ch.ffhs.drugstore.domain.service.DrugManagementService;
import ch.ffhs.drugstore.domain.service.SignatureService;
import ch.ffhs.drugstore.domain.usecase.management.signatures.GetSignatures;
import ch.ffhs.drugstore.presentation.dispensary.view.FilterState;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugDto;
import ch.ffhs.drugstore.shared.dto.management.signature.SignatureDto;
import util.TestUtil;

public class GetAllDispensaryItemsTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private DispensaryService dispensaryService;

    @Before
    public void setUp() {
        dispensaryService = mock(DispensaryService.class);
    }

    @After
    public void tearDown() {
        dispensaryService = null;
    }
    @Test
    public void executeGetAllDispensaryItemsUseCaseSuccessfully() {
        // Arrange
        FilterState<Integer> filterState = mock(FilterState.class);
        MutableLiveData<List<DrugDto>> liveDataDrugList = new MutableLiveData<>();
        List<DrugDto> drugDtoList = new ArrayList<>();
        drugDtoList.add(TestUtil.createDrugDto(1));
        drugDtoList.add(TestUtil.createDrugDto(2));
        liveDataDrugList.postValue(drugDtoList);
        when(dispensaryService.getAllDrugs(filterState)).thenReturn(liveDataDrugList);

        // Act
        GetAllDispensaryItems getAllDispensaryItems = new GetAllDispensaryItems(dispensaryService);
        LiveData<List<DrugDto>> actualDrugs = getAllDispensaryItems.execute(filterState);

        // Verify
        verify(dispensaryService, times(1)).getAllDrugs(any());
        assertEquals(liveDataDrugList, actualDrugs);
    }
}