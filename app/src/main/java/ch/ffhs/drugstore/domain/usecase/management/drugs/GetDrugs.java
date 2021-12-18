package ch.ffhs.drugstore.domain.usecase.management.drugs;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.domain.service.DrugManagementService;
import ch.ffhs.drugstore.domain.usecase.UseCase;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugDto;
/**
 * Use-Case class to get an existing drug
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class GetDrugs implements UseCase<LiveData<List<DrugDto>>, Void> {
  @Inject DrugManagementService drugManagementService;

  @Inject
  public GetDrugs(DrugManagementService drugManagementService) {
    this.drugManagementService = drugManagementService;
  }

  /**
   * @return all drugs
   */
  @Override
  public LiveData<List<DrugDto>> execute(Void unused) {
    return drugManagementService.getAllDrugs();
  }
}
