package ch.ffhs.drugstore.presentation.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.data.entity.Drug;
import ch.ffhs.drugstore.domain.usecase.management.GetDrugs;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class DrugsViewModel extends AndroidViewModel {
  @Inject GetDrugs getDrugs;
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
}
