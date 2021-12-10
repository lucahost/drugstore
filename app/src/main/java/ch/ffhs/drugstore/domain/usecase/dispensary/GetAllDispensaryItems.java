package ch.ffhs.drugstore.domain.usecase.dispensary;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.data.dto.DrugDto;
import ch.ffhs.drugstore.domain.service.DispensaryService;
import ch.ffhs.drugstore.domain.usecase.UseCase;
import ch.ffhs.drugstore.presentation.dispensary.view.DispensaryFilters;
import ch.ffhs.drugstore.presentation.dispensary.view.FilterState;

public class GetAllDispensaryItems implements UseCase<LiveData<List<DrugDto>>, FilterState<DispensaryFilters>> {
  @Inject DispensaryService dispensaryService;

  @Inject
  public GetAllDispensaryItems(DispensaryService dispensaryService) {
    this.dispensaryService = dispensaryService;
  }

  @Override
  public LiveData<List<DrugDto>> execute(FilterState<DispensaryFilters> filterState) {
    return dispensaryService.getAllDrugs(filterState);
  }
}
