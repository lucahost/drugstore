package ch.ffhs.drugstore.domain.usecase.management.drugs;

import javax.inject.Inject;

import ch.ffhs.drugstore.domain.service.DrugManagementService;
import ch.ffhs.drugstore.domain.usecase.UseCase;

public class DeleteDrug implements UseCase<Void, Integer> {
  @Inject DrugManagementService drugManagementService;

  @Inject
  public DeleteDrug(DrugManagementService drugManagementService) {
    this.drugManagementService = drugManagementService;
  }

  @Override
  public Void execute(Integer drugId) {
    return drugManagementService.deleteDrug(drugId);
  }
}
