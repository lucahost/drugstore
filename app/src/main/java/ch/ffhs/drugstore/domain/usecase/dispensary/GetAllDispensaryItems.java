package ch.ffhs.drugstore.domain.usecase.dispensary;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.domain.service.DispensaryService;
import ch.ffhs.drugstore.domain.usecase.UseCase;
import ch.ffhs.drugstore.presentation.dispensary.view.FilterState;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugDto;

public class GetAllDispensaryItems implements UseCase<LiveData<List<DrugDto>>, FilterState<Integer>> {
  @Inject DispensaryService dispensaryService;

  @Inject
  public GetAllDispensaryItems(DispensaryService dispensaryService) {
    this.dispensaryService = dispensaryService;
  }

  @Override
  public LiveData<List<DrugDto>> execute(FilterState<Integer> filterState) {
    return dispensaryService.getAllDrugs(filterState);
  }
}
