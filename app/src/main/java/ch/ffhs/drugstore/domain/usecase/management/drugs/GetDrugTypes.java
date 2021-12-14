package ch.ffhs.drugstore.domain.usecase.management.drugs;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.data.dto.DrugTypeDto;
import ch.ffhs.drugstore.domain.service.DrugManagementService;
import ch.ffhs.drugstore.domain.usecase.UseCase;

public class GetDrugTypes implements UseCase<LiveData<List<DrugTypeDto>>, Void> {
  @Inject DrugManagementService drugManagementService;

  @Inject
  public GetDrugTypes(DrugManagementService drugManagementService) {
    this.drugManagementService = drugManagementService;
  }

  @Override
  public LiveData<List<DrugTypeDto>> execute(Void params) {
    return drugManagementService.getAllDrugTypes();
  }
}
