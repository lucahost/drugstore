package ch.ffhs.drugstore.domain.usecase.dispensary;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.data.dto.DrugDto;
import ch.ffhs.drugstore.data.entity.Drug;
import ch.ffhs.drugstore.domain.service.DispensaryService;
import ch.ffhs.drugstore.domain.usecase.UseCase;

public class GetAllDispensaryItems implements UseCase<LiveData<List<DrugDto>>, Void> {
  @Inject DispensaryService dispensaryService;

  @Inject
  public GetAllDispensaryItems(DispensaryService dispensaryService) {
    this.dispensaryService = dispensaryService;
  }

  @Override
  public LiveData<List<DrugDto>> execute(Void unused) {
    return dispensaryService.getAllDrugs();
  }
}
