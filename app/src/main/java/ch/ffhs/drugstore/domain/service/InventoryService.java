package ch.ffhs.drugstore.domain.service;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.data.entity.InventoryDrug;

public class InventoryService {
  @Inject
  public InventoryService() {
    // TODO document why this constructor is empty
  }

  public LiveData<List<InventoryDrug>> getInventory() {
    // Still fakin' the actual data.
    List<InventoryDrug> inventory = new ArrayList<>();
    inventory.add(new InventoryDrug(1, 1, (long) 1.0, true));
    inventory.add(new InventoryDrug(2, 2, (long) 1.0, false));
    inventory.add(new InventoryDrug(3, 3, (long) 3.0, true));
    inventory.add(new InventoryDrug(4, 4, (long) 2.0, false));
    return new MutableLiveData<>(inventory);
  }
}
