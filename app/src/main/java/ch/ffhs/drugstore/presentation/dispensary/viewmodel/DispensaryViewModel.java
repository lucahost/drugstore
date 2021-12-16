package ch.ffhs.drugstore.presentation.dispensary.viewmodel;

import static ch.ffhs.drugstore.shared.extensions.DoubleExtension.tryParseDouble;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import ch.ffhs.drugstore.domain.usecase.dispensary.DispenseDrug;
import ch.ffhs.drugstore.domain.usecase.dispensary.GetAllDispensaryItems;
import ch.ffhs.drugstore.domain.usecase.dispensary.ToggleDrugIsFavorite;
import ch.ffhs.drugstore.domain.usecase.management.drugs.GetDrugTypes;
import ch.ffhs.drugstore.presentation.dispensary.view.FilterState;
import ch.ffhs.drugstore.shared.dto.dispensary.SubmitDispenseDto;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugDto;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugTypeDto;
import ch.ffhs.drugstore.shared.exceptions.DrugstoreException;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class DispensaryViewModel extends AndroidViewModel {
    private final LiveData<List<DrugDto>> items;
    private LiveData<List<DrugTypeDto>> drugTypes;
    private final MutableLiveData<FilterState<Integer>> filterState = new MutableLiveData<>();
    @Inject
    GetAllDispensaryItems getAllDispensaryItems;
    @Inject
    ToggleDrugIsFavorite toggleDrugIsFavorite;
    @Inject
    DispenseDrug dispenseDrug;
    @Inject GetDrugTypes getDrugTypes;

    @Inject
    public DispensaryViewModel(Application application) {
        super(application);
        filterState.setValue(new FilterState<>());
        items = Transformations.switchMap(filterState, f
                -> getAllDispensaryItems.execute(Objects.requireNonNull(filterState.getValue())));
    }

    public LiveData<List<DrugDto>> getItems() {
        return items;
    }

  public LiveData<FilterState<Integer>> getFilterState() {
    return filterState;
  }

    public LiveData<List<DrugTypeDto>> getDrugTypes() {
        if (drugTypes == null) {
          drugTypes = getDrugTypes.execute(null);
        }
        return drugTypes;
    }

  public void filter(FilterState<Integer> filters) {
    filterState.postValue(filters);
  }

    public void toggleDrugIsFavorite(int drugId) {
        toggleDrugIsFavorite.execute(drugId);
    }

    public void dispenseDrug(int drugId, String employee, String patient, String sDosage)
            throws DrugstoreException {
        double dosage = tryParseDouble(sDosage);
        SubmitDispenseDto submitDispenseDto = new SubmitDispenseDto(drugId, employee, patient,
                dosage);
        dispenseDrug.execute(submitDispenseDto);
    }
}
