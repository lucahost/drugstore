package ch.ffhs.drugstore.domain.usecase.management.drugs;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.domain.service.DrugManagementService;
import ch.ffhs.drugstore.domain.usecase.UseCase;
import ch.ffhs.drugstore.shared.dto.management.drugs.UnitDto;

public class GetDrugUnits implements UseCase<LiveData<List<UnitDto>>, Void> {
  @Inject DrugManagementService drugManagementService;

  @Inject
  public GetDrugUnits(DrugManagementService drugManagementService) {
    this.drugManagementService = drugManagementService;
  }

  @Override
  public LiveData<List<UnitDto>> execute(Void params) {
    return drugManagementService.getAllUnits();
  }
}
