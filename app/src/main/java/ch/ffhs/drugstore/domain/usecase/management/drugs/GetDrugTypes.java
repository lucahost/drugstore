package ch.ffhs.drugstore.domain.usecase.management.drugs;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.domain.service.DrugManagementService;
import ch.ffhs.drugstore.domain.usecase.UseCase;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugTypeDto;
/**
 * Use-Case class to return all drug types
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class GetDrugTypes implements UseCase<LiveData<List<DrugTypeDto>>, Void> {
  @Inject DrugManagementService drugManagementService;

  @Inject
  public GetDrugTypes(DrugManagementService drugManagementService) {
    this.drugManagementService = drugManagementService;
  }

  /**
   * @return all drug types
   */
  @Override
  public LiveData<List<DrugTypeDto>> execute(Void params) {
    return drugManagementService.getAllDrugTypes();
  }
}
