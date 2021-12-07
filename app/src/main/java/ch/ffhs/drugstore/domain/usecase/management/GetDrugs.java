package ch.ffhs.drugstore.domain.usecase.management;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.data.entity.Drug;
import ch.ffhs.drugstore.domain.service.DrugManagementService;
import ch.ffhs.drugstore.domain.usecase.UseCase;

public class GetDrugs implements UseCase<LiveData<List<Drug>>, Void> {
  @Inject DrugManagementService drugManagementService;

  @Inject
  public GetDrugs(DrugManagementService drugManagementService) {
    this.drugManagementService = drugManagementService;
  }

  @Override
  public LiveData<List<Drug>> execute(Void unused) {
    return drugManagementService.getAllDrugs();
  }
}
