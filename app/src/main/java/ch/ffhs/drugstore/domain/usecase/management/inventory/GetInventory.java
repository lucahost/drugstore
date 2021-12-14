package ch.ffhs.drugstore.domain.usecase.management.inventory;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.domain.service.InventoryService;
import ch.ffhs.drugstore.domain.usecase.UseCase;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugDto;

public class GetInventory implements UseCase<LiveData<List<DrugDto>>, Void> {
  @Inject InventoryService inventoryService;

  @Inject
  public GetInventory(InventoryService inventoryService) {
    this.inventoryService = inventoryService;
  }

  @Override
  public LiveData<List<DrugDto>> execute(Void unused) {
    return inventoryService.getInventory();
  }
}
