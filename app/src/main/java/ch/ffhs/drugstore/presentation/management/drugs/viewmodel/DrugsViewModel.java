package ch.ffhs.drugstore.presentation.management.drugs.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.shared.dto.management.drugs.DrugTypeDto;
import ch.ffhs.drugstore.shared.dto.management.drugs.UnitDto;
import ch.ffhs.drugstore.domain.usecase.management.drugs.AddDrug;
import ch.ffhs.drugstore.domain.usecase.management.drugs.CreateDrug;
import ch.ffhs.drugstore.domain.usecase.management.drugs.DeleteDrug;
import ch.ffhs.drugstore.domain.usecase.management.drugs.EditDrug;
import ch.ffhs.drugstore.domain.usecase.management.drugs.GetDrugTypes;
import ch.ffhs.drugstore.domain.usecase.management.drugs.GetDrugUnits;
import ch.ffhs.drugstore.domain.usecase.management.drugs.GetDrugs;
import ch.ffhs.drugstore.domain.usecase.management.drugs.RemoveDrug;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugDto;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class DrugsViewModel extends AndroidViewModel {
  @Inject GetDrugs getDrugs;
  @Inject CreateDrug createDrug;
  @Inject AddDrug addDrug;
  @Inject EditDrug editDrug;
  @Inject DeleteDrug deleteDrug;
  @Inject RemoveDrug removeDrug;
  @Inject GetDrugTypes getDrugTypes;
  @Inject GetDrugUnits getDrugUnits;
  private LiveData<List<DrugDto>> drugs;
  private LiveData<List<DrugTypeDto>> drugTypes;
  private LiveData<List<UnitDto>> drugUnits;

  @Inject
  public DrugsViewModel(Application application) {
    super(application);
  }

  public LiveData<List<DrugDto>> getDrugs() {
    if (drugs == null) {
      drugs = getDrugs.execute(null);
    }
    return drugs;
  }

  public LiveData<List<DrugTypeDto>> getDrugTypes() {
    if (drugTypes == null) {
      drugTypes = getDrugTypes.execute(null);
    }
    return drugTypes;
  }

  public LiveData<List<UnitDto>> getDrugUnits() {
    if (drugUnits == null) {
      drugUnits = getDrugUnits.execute(null);
    }
    return drugUnits;
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
