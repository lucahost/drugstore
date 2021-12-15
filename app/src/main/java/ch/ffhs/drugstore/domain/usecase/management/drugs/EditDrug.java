package ch.ffhs.drugstore.domain.usecase.management.drugs;

import javax.inject.Inject;

import ch.ffhs.drugstore.domain.service.DrugManagementService;
import ch.ffhs.drugstore.domain.usecase.UseCase;
import ch.ffhs.drugstore.shared.dto.management.drugs.EditDrugDto;

public class EditDrug implements UseCase<Void, EditDrugDto> {
  @Inject DrugManagementService drugManagementService;

  @Inject
  public EditDrug(DrugManagementService drugManagementService) {
    this.drugManagementService = drugManagementService;
  }

  @Override
  public Void execute(EditDrugDto editDrugDto) {
    drugManagementService.editDrug(editDrugDto);
    return null;
  }
}
