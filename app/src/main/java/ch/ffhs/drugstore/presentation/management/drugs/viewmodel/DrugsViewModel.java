package ch.ffhs.drugstore.presentation.management.drugs.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.data.entity.Drug;
import ch.ffhs.drugstore.domain.usecase.management.drugs.AddDrug;
import ch.ffhs.drugstore.domain.usecase.management.drugs.CreateDrug;
import ch.ffhs.drugstore.domain.usecase.management.drugs.DeleteDrug;
import ch.ffhs.drugstore.domain.usecase.management.drugs.EditDrug;
import ch.ffhs.drugstore.domain.usecase.management.drugs.GetDrugs;
import ch.ffhs.drugstore.domain.usecase.management.drugs.RemoveDrug;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class DrugsViewModel extends AndroidViewModel {
  @Inject GetDrugs getDrugs;
  @Inject CreateDrug createDrug;
  @Inject AddDrug addDrug;
  @Inject EditDrug editDrug;
  @Inject DeleteDrug deleteDrug;
  @Inject RemoveDrug removeDrug;
  private LiveData<List<Drug>> items;

  @Inject
  public DrugsViewModel(Application application) {
    super(application);
  }

  public LiveData<List<Drug>> getItems() {
    if (items == null) {
      items = getDrugs.execute(null);
    }
    return items;
  }

  public void addDrug() {
    addDrug.execute(null);
  }

  public void editDrug() {
    editDrug.execute(null);
  }

  public void createDrug() {
    createDrug.execute(null);
  }

  public void deleteDrug() {
    deleteDrug.execute(null);
  }

  public void removeDrug() {
    removeDrug.execute(null);
  }
}
