package ch.ffhs.drugstore.domain.usecase.management.drugs;

import javax.inject.Inject;

import ch.ffhs.drugstore.domain.service.DrugManagementService;
import ch.ffhs.drugstore.domain.usecase.UseCase;

public class EditDrug implements UseCase<Void, Void> {
  @Inject DrugManagementService drugManagementService;

  @Inject
  public EditDrug(DrugManagementService drugManagementService) {
    this.drugManagementService = drugManagementService;
  }

  @Override
  public Void execute(Void unused) {
    return null;
  }
}
