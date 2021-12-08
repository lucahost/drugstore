package ch.ffhs.drugstore.presentation.dispensary.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.data.entity.Drug;
import ch.ffhs.drugstore.domain.usecase.dispensary.AddToFavorites;
import ch.ffhs.drugstore.domain.usecase.dispensary.DispenseDrug;
import ch.ffhs.drugstore.domain.usecase.dispensary.GetAllDispensaryItems;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class DispensaryViewModel extends AndroidViewModel {
  @Inject GetAllDispensaryItems getAllDispensaryItems;
  @Inject AddToFavorites addToFavorites;
  @Inject DispenseDrug dispenseDrug;
  private LiveData<List<Drug>> items;

  @Inject
  public DispensaryViewModel(Application application) {
    super(application);
  }

  public LiveData<List<Drug>> getItems() {
    if (items == null) {
      items = getAllDispensaryItems.execute(null);
    }
    return items;
  }

  public void addToFavorites() {
    addToFavorites.execute(null);
  }

  public void dispenseDrug() {
    dispenseDrug.execute(null);
  }
}
