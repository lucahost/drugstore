package ch.ffhs.drugstore.domain.usecase.management.inventory;

import javax.inject.Inject;

import ch.ffhs.drugstore.domain.service.DrugManagementService;
import ch.ffhs.drugstore.domain.usecase.UseCase;

public class ToggleInventoryItem implements UseCase<Void, Void> {
  @Inject DrugManagementService drugManagementService;

  @Inject
  public ToggleInventoryItem(DrugManagementService drugManagementService) {
    this.drugManagementService = drugManagementService;
  }

  @Override
  public Void execute(Void unused) {
    return null;
  }
}
