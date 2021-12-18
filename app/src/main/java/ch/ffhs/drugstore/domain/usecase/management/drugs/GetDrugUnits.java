package ch.ffhs.drugstore.domain.usecase.management.drugs;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.domain.service.DrugManagementService;
import ch.ffhs.drugstore.domain.usecase.UseCase;
import ch.ffhs.drugstore.shared.dto.management.drugs.UnitDto;
/**
 * Use-Case class to get all drug units
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class GetDrugUnits implements UseCase<LiveData<List<UnitDto>>, Void> {
  @Inject DrugManagementService drugManagementService;

  @Inject
  public GetDrugUnits(DrugManagementService drugManagementService) {
    this.drugManagementService = drugManagementService;
  }

  /**
   * @return all drug units
   */
  @Override
  public LiveData<List<UnitDto>> execute(Void params) {
    return drugManagementService.getAllUnits();
  }
}
