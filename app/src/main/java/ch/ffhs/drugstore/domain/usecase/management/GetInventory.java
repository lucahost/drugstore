package ch.ffhs.drugstore.domain.usecase.management;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.data.entity.InventoryDrug;
import ch.ffhs.drugstore.domain.service.InventoryService;
import ch.ffhs.drugstore.domain.usecase.UseCase;

public class GetInventory implements UseCase<LiveData<List<InventoryDrug>>, Void> {
  @Inject InventoryService inventoryService;

  @Inject
  public GetInventory(InventoryService historyService) {
    this.inventoryService = inventoryService;
  }

  @Override
  public LiveData<List<InventoryDrug>> execute(Void unused) {
    return inventoryService.getInventory();
  }
}
