package ch.ffhs.drugstore.domain.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.junit.Test;

import java.util.List;

import ch.ffhs.drugstore.data.repository.DrugRepository;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugDto;

public class InventoryServiceTest {

    @Test
    public void getInventory() {
        // Setup
        LiveData<List<DrugDto>> drugs = new MutableLiveData<>();
        DrugRepository drugRepository = mock(DrugRepository.class);
        when(drugRepository.getAllDrugs()).thenReturn(drugs);

        InventoryService inventoryService = new InventoryService(drugRepository);

        // Test
        LiveData<List<DrugDto>> result = inventoryService.getInventory();

        // Assert
        verify(drugRepository, times(1)).getAllDrugs();
    }
}