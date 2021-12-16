package ch.ffhs.drugstore.presentation.management.drugs.viewmodel;

import static ch.ffhs.drugstore.shared.extensions.DoubleExtension.tryParseDouble;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.shared.dto.management.drugs.UpdateDrugAmountDto;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugTypeDto;
import ch.ffhs.drugstore.shared.dto.management.drugs.EditDrugDto;
import ch.ffhs.drugstore.shared.dto.management.drugs.UnitDto;
import ch.ffhs.drugstore.domain.usecase.management.drugs.UpdateDrugAmount;
import ch.ffhs.drugstore.domain.usecase.management.drugs.CreateDrug;
import ch.ffhs.drugstore.domain.usecase.management.drugs.DeleteDrug;
import ch.ffhs.drugstore.domain.usecase.management.drugs.EditDrug;
import ch.ffhs.drugstore.domain.usecase.management.drugs.GetDrugTypes;
import ch.ffhs.drugstore.domain.usecase.management.drugs.GetDrugUnits;
import ch.ffhs.drugstore.domain.usecase.management.drugs.GetDrugs;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugDto;
import ch.ffhs.drugstore.shared.dto.management.drugs.CreateDrugDto;
import ch.ffhs.drugstore.shared.exceptions.DrugstoreException;
import ch.ffhs.drugstore.shared.extensions.DoubleExtension;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class DrugsViewModel extends AndroidViewModel {
    @Inject
    GetDrugs getDrugs;
    @Inject
    CreateDrug createDrug;
    @Inject
    UpdateDrugAmount updateDrugAmount;
    @Inject
    EditDrug editDrug;
    @Inject
    DeleteDrug deleteDrug;
    @Inject
    GetDrugTypes getDrugTypes;
    @Inject
    GetDrugUnits getDrugUnits;
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

    public void updateDrugAmount(int drugId, String sAmount) throws DrugstoreException {
        double amount = tryParseDouble(sAmount);
        UpdateDrugAmountDto updateDrugAmountDto = new UpdateDrugAmountDto(drugId, amount);
        updateDrugAmount.execute(updateDrugAmountDto);
    }

    public void editDrug(int drugId, String name, String dosage, int drugTypeId, int unitId,
            String sTolerance, boolean isFavorite) {
        double tolerance = tryParseDouble(sTolerance);
        EditDrugDto editDrugDto = new EditDrugDto(drugId, name, dosage, drugTypeId, unitId, name,
                tolerance, isFavorite);
        editDrug.execute(editDrugDto);
    }

    public void createDrug(String name, String dosage, int drugTypeId, int unitId,
            String sTolerance, boolean isFavorite)
            throws Exception {
        double tolerance = tryParseDouble(sTolerance);
        CreateDrugDto createDrugDto = new CreateDrugDto(name, dosage, drugTypeId, unitId, name,
                tolerance, isFavorite);
        createDrug.execute(createDrugDto);
    }

    public void deleteDrug(int drugId) {
        deleteDrug.execute(drugId);
    }
}
