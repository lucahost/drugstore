package ch.ffhs.drugstore.domain.usecase.management.drugs;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.data.dto.DrugDto;
import ch.ffhs.drugstore.data.entity.Drug;
import ch.ffhs.drugstore.domain.service.DrugManagementService;
import ch.ffhs.drugstore.domain.usecase.UseCase;

public class GetDrugs implements UseCase<LiveData<List<DrugDto>>, Void> {
  @Inject DrugManagementService drugManagementService;

  @Inject
  public GetDrugs(DrugManagementService drugManagementService) {
    this.drugManagementService = drugManagementService;
  }

  @Override
  public LiveData<List<DrugDto>> execute(Void unused) {
    return drugManagementService.getAllDrugs();
  }
}
