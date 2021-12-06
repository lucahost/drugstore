package ch.ffhs.drugstore.domain.usecase.dispensary;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.data.entity.Drug;
import ch.ffhs.drugstore.data.entity.Todo;
import ch.ffhs.drugstore.domain.service.DrugService;
import ch.ffhs.drugstore.domain.service.TodoService;
import ch.ffhs.drugstore.domain.usecase.UseCase;

public class GetAllDrugs implements UseCase<LiveData<List<Drug>>, Void> {
  @Inject
  DrugService drugService;

  @Inject
  public GetAllDrugs(TodoService todoService) {
    this.drugService = drugService;
  }

  @Override
  public LiveData<List<Drug>> execute(Void unused) {
    return drugService.getAllDrugs();
  }
}
