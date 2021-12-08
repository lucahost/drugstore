package ch.ffhs.drugstore.domain.usecase.dispensary;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.data.entity.Drug;
import ch.ffhs.drugstore.domain.service.DispensaryService;
import ch.ffhs.drugstore.domain.usecase.UseCase;

public class GetAllDispensaryItems implements UseCase<LiveData<List<Drug>>, Void> {
  @Inject DispensaryService dispensaryService;

  @Inject
  public GetAllDispensaryItems(DispensaryService dispensaryService) {
    this.dispensaryService = dispensaryService;
  }

  @Override
  public LiveData<List<Drug>> execute(Void unused) {
    return dispensaryService.getAllDrugs();
  }
}
