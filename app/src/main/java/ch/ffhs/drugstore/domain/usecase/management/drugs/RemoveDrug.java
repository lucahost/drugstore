package ch.ffhs.drugstore.domain.usecase.management.drugs;

import javax.inject.Inject;

import ch.ffhs.drugstore.domain.service.DrugManagementService;
import ch.ffhs.drugstore.domain.usecase.UseCase;

public class RemoveDrug implements UseCase<Void, Void> {
  @Inject DrugManagementService drugManagementService;

  @Inject
  public RemoveDrug(DrugManagementService drugManagementService) {
    this.drugManagementService = drugManagementService;
  }

  @Override
  public Void execute(Void unused) {
    return null;
  }
}
