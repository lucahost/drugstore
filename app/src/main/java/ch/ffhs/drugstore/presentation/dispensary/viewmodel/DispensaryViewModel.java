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

/**
 * View model for the dispensary.
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
@HiltViewModel
public class DispensaryViewModel extends AndroidViewModel {
    private final ToggleDrugIsFavorite toggleDrugIsFavorite;
    private final DispenseDrug dispenseDrug;
    private final GetDrugTypes getDrugTypes;
    private final LiveData<List<DrugDto>> dispensaryItems;
    private final MutableLiveData<FilterState<Integer>> filterState = new MutableLiveData<>();
    private LiveData<List<DrugTypeDto>> drugTypes;

    /**
     * Constructs a {@link DispensaryViewModel}
     *
     * @param application           global application state
     * @param getAllDispensaryItems use case to get all dispensary items
     * @param toggleDrugIsFavorite  use case to toggle favorite on a drug
     * @param dispenseDrug          use case to dispense a drug
     * @param getDrugTypes          use case to get drug types
     */
    @Inject
    public DispensaryViewModel(Application application, GetAllDispensaryItems getAllDispensaryItems,
            ToggleDrugIsFavorite toggleDrugIsFavorite, DispenseDrug dispenseDrug,
            GetDrugTypes getDrugTypes) {
        super(application);
        this.toggleDrugIsFavorite = toggleDrugIsFavorite;
        this.dispenseDrug = dispenseDrug;
        this.getDrugTypes = getDrugTypes;
        filterState.setValue(new FilterState<>());
        dispensaryItems = Transformations.switchMap(filterState, f
                -> getAllDispensaryItems.execute(Objects.requireNonNull(filterState.getValue())));
    }

    /**
     * Get filtered dispensary items
     *
     * @return dispensary items
     */
    public LiveData<List<DrugDto>> getDispensaryItems() {
        return dispensaryItems;
    }

    /**
     * Get the current filter state
     *
     * @return filter state
     */
    public LiveData<FilterState<Integer>> getFilterState() {
        return filterState;
    }

    /**
     * Get all drug types
     *
     * @return drug types
     */
    public LiveData<List<DrugTypeDto>> getDrugTypes() {
        if (drugTypes == null) {
            drugTypes = getDrugTypes.execute(null);
        }
        return drugTypes;
    }

    /**
     * Update the dispensary item filter
     *
     * @param filters filter state
     */
    public void filter(FilterState<Integer> filters) {
        filterState.postValue(filters);
    }

    /**
     * Toggle favorite flag on a drug
     *
     * @param drugId the drug id to toggle favorite flag
     * @throws DrugstoreException if toggling favorite flag goes wrong
     */
    public void toggleDrugIsFavorite(int drugId) throws DrugstoreException {
        toggleDrugIsFavorite.execute(drugId);
    }

    /**
     * @param drugId   the id of the drug
     * @param employee the employee dispensing the drug
     * @param patient  the patient receiving the drug
     * @param sDosage  the dosage to be dispensed
     * @throws DrugstoreException if dispensation of the drug goes wrong
     */
    public void dispenseDrug(int drugId, String employee, String patient, String sDosage)
            throws DrugstoreException {
        double dosage = tryParseDouble(sDosage);
        SubmitDispenseDto submitDispenseDto = new SubmitDispenseDto(drugId, employee, patient,
                dosage);
        dispenseDrug.execute(submitDispenseDto);
    }
}
