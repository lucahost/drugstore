package ch.ffhs.drugstore.domain.usecase.management.inventory;

import javax.inject.Inject;

import ch.ffhs.drugstore.domain.service.DrugManagementService;
import ch.ffhs.drugstore.domain.usecase.UseCase;

public class SignInventory implements UseCase<Void, Void> {
  @Inject DrugManagementService drugManagementService;

  @Inject
  public SignInventory(DrugManagementService drugManagementService) {
    this.drugManagementService = drugManagementService;
  }

  @Override
  public Void execute(Void unused) {
    return null;
  }
}
