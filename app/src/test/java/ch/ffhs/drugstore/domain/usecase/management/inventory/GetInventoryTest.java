package ch.ffhs.drugstore.domain.usecase.management.inventory;

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

import ch.ffhs.drugstore.domain.service.InventoryService;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugDto;
import util.TestUtil;

public class GetInventoryTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private InventoryService inventoryService;

    @Before
    public void setUp() {
        inventoryService = mock(InventoryService.class);
    }

    @After
    public void tearDown() {
        inventoryService = null;
    }

    @Test
    public void executeGetInventoryUseCaseSuccessfully() {
        // Arrange
        MutableLiveData<List<DrugDto>> liveDataDrugList = new MutableLiveData<>();
        List<DrugDto> drugList = new ArrayList<>();
        drugList.add(TestUtil.createDrugDto(1));
        drugList.add(TestUtil.createDrugDto(2));
        liveDataDrugList.postValue(drugList);

        when(inventoryService.getInventory()).thenReturn(liveDataDrugList);

        // Act
        GetInventory getInventory = new GetInventory(inventoryService);
        LiveData<List<DrugDto>> actualDrugs = getInventory.execute(null);

        // Verify
        verify(inventoryService, times(1)).getInventory();
        assertEquals(2, actualDrugs.getValue().size());
    }
}