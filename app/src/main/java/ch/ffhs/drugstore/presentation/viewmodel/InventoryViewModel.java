package ch.ffhs.drugstore.presentation.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.data.entity.InventoryDrug;
import ch.ffhs.drugstore.domain.usecase.management.inventory.GetInventory;
import ch.ffhs.drugstore.domain.usecase.management.inventory.SignInventory;
import ch.ffhs.drugstore.domain.usecase.management.inventory.ToggleInventoryItem;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class InventoryViewModel extends AndroidViewModel {
  @Inject GetInventory getInventory;
  @Inject ToggleInventoryItem toggleInventoryItem;
  @Inject SignInventory signInventory;
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

  public void signInventory() {
    signInventory.execute(null);
  }

  public void toggleInventoryItem() {
    toggleInventoryItem.execute(null);
  }
}
