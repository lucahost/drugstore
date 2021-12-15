package ch.ffhs.drugstore.domain.usecase.management.drugs;

import javax.inject.Inject;

import ch.ffhs.drugstore.domain.service.DrugManagementService;
import ch.ffhs.drugstore.domain.usecase.UseCase;
import ch.ffhs.drugstore.shared.dto.management.drugs.CreateDrugDto;

public class CreateDrug implements UseCase<Void, CreateDrugDto> {
  @Inject DrugManagementService drugManagementService;

  @Inject
  public CreateDrug(DrugManagementService drugManagementService) {
    this.drugManagementService = drugManagementService;
  }

  @Override
  public Void execute(CreateDrugDto createDrugDto) throws Exception {
    return drugManagementService.createDrug(createDrugDto);
  }
}
