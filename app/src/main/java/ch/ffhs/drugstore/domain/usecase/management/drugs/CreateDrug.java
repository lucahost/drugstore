package ch.ffhs.drugstore.domain.usecase.management.drugs;

import javax.inject.Inject;

import ch.ffhs.drugstore.domain.service.DrugManagementService;
import ch.ffhs.drugstore.domain.usecase.UseCase;
import ch.ffhs.drugstore.shared.dto.management.drugs.CreateDrugDto;

/**
 * Use-Case class to create a new drug
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class CreateDrug implements UseCase<Void, CreateDrugDto> {
  @Inject DrugManagementService drugManagementService;

  @Inject
  public CreateDrug(DrugManagementService drugManagementService) {
    this.drugManagementService = drugManagementService;
  }

  /**
   * method to create drugs
   * @param createDrugDto
   * @throws Exception
   */
  @Override
  public Void execute(CreateDrugDto createDrugDto) throws Exception {
    drugManagementService.createDrug(createDrugDto);
    return null;
  }
}
