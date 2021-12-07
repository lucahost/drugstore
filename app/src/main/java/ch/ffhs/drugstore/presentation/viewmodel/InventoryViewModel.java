package ch.ffhs.drugstore.presentation.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.data.entity.InventoryDrug;
import ch.ffhs.drugstore.domain.usecase.management.GetInventory;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class InventoryViewModel extends AndroidViewModel {
  @Inject GetInventory getInventory;
  private LiveData<List<InventoryDrug>> items;

  @Inject
  public InventoryViewModel(Application application) {
    super(application);
  }

  public LiveData<List<InventoryDrug>> getItems() {
    if (items == null) {
      items = getInventory.execute(null);
    }
    return items;
  }
}
