package ch.ffhs.drugstore.presentation.management.drugs.viewmodel;

import static ch.ffhs.drugstore.shared.extensions.DoubleExtension.tryParseDouble;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.domain.usecase.management.drugs.CreateDrug;
import ch.ffhs.drugstore.domain.usecase.management.drugs.DeleteDrug;
import ch.ffhs.drugstore.domain.usecase.management.drugs.EditDrug;
import ch.ffhs.drugstore.domain.usecase.management.drugs.GetDrugTypes;
import ch.ffhs.drugstore.domain.usecase.management.drugs.GetDrugUnits;
import ch.ffhs.drugstore.domain.usecase.management.drugs.GetDrugs;
import ch.ffhs.drugstore.domain.usecase.management.drugs.GetSubstances;
import ch.ffhs.drugstore.domain.usecase.management.drugs.UpdateDrugAmount;
import ch.ffhs.drugstore.shared.dto.management.drugs.CreateDrugDto;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugDto;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugTypeDto;
import ch.ffhs.drugstore.shared.dto.management.drugs.EditDrugDto;
import ch.ffhs.drugstore.shared.dto.management.drugs.SubstanceDto;
import ch.ffhs.drugstore.shared.dto.management.drugs.UnitDto;
import ch.ffhs.drugstore.shared.dto.management.drugs.UpdateDrugAmountDto;
import ch.ffhs.drugstore.shared.exceptions.DrugstoreException;
import dagger.hilt.android.lifecycle.HiltViewModel;

/**
 * View model for drugs management.
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
@HiltViewModel
public class DrugsViewModel extends AndroidViewModel {
    private final GetDrugs getDrugs;
    private final CreateDrug createDrug;
    private final UpdateDrugAmount updateDrugAmount;
    private final EditDrug editDrug;
    private final DeleteDrug deleteDrug;
    private final GetDrugTypes getDrugTypes;
    private final GetDrugUnits getDrugUnits;
    private final GetSubstances getSubstances;
    private LiveData<List<DrugDto>> drugs;
    private LiveData<List<DrugTypeDto>> drugTypes;
    private LiveData<List<UnitDto>> drugUnits;
    private LiveData<List<SubstanceDto>> substances;

    /**
     * Constructs a {@link DrugsViewModel}
     *
     * @param application      global application state
     * @param getDrugs         use case to get drugs
     * @param createDrug       use case to create a drug
     * @param updateDrugAmount use case to update amount on a drug
     * @param editDrug         use case to edit a drug
     * @param deleteDrug       use case to delete a drug
     * @param getDrugTypes     use case to get drug types
     * @param getDrugUnits     use case to get drug units
     * @param getSubstances    use case to get substances
     */
    @Inject
    public DrugsViewModel(
            Application application,
            GetDrugs getDrugs,
            CreateDrug createDrug,
            UpdateDrugAmount updateDrugAmount,
            EditDrug editDrug,
            DeleteDrug deleteDrug,
            GetDrugTypes getDrugTypes,
            GetDrugUnits getDrugUnits,
            GetSubstances getSubstances) {
        super(application);
        this.getDrugs = getDrugs;
        this.createDrug = createDrug;
        this.updateDrugAmount = updateDrugAmount;
        this.editDrug = editDrug;
        this.deleteDrug = deleteDrug;
        this.getDrugTypes = getDrugTypes;
        this.getDrugUnits = getDrugUnits;
        this.getSubstances = getSubstances;
    }

    /**
     * Get all drugs
     *
     * @return list of drugs
     */
    public LiveData<List<DrugDto>> getDrugs() {
        if (drugs == null) {
            drugs = getDrugs.execute(null);
        }
        return drugs;
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
     * Get all drug units
     *
     * @return drug units
     */
    public LiveData<List<UnitDto>> getDrugUnits() {
        if (drugUnits == null) {
            drugUnits = getDrugUnits.execute(null);
        }
        return drugUnits;
    }

    /**
     * Get all substances
     *
     * @return substances
     */
    public LiveData<List<SubstanceDto>> getSubstances() {
        if (substances == null) {
            substances = getSubstances.execute(null);
        }
        return substances;
    }

    /**
     * Update the amount on a specific drug by id
     *
     * @param drugId  drug id to be updated
     * @param sAmount the amount to be added
     * @throws DrugstoreException if updating the drug amount goes wrong
     */
    public void updateDrugAmount(int drugId, String sAmount, String userShortName)
            throws DrugstoreException {
        double amount = tryParseDouble(sAmount);
        UpdateDrugAmountDto updateDrugAmountDto =
                new UpdateDrugAmountDto(drugId, amount, userShortName);
        updateDrugAmount.execute(updateDrugAmountDto);
    }

    /**
     * Edit an existing drug by id
     *
     * @param drugId     id of the drug to be edited
     * @param name       name of the drug
     * @param substance  substance of the drug
     * @param dosage     dosage of the drug
     * @param drugTypeId drug type id of the drug
     * @param unitId     unit id of the drug
     * @param sTolerance tolerance of the drug
     * @param isFavorite if the drug is a favorite
     */
    public void editDrug(
            int drugId,
            String name,
            String substance,
            String dosage,
            int drugTypeId,
            int unitId,
            String sTolerance,
            boolean isFavorite)
            throws DrugstoreException {
        double tolerance = tryParseDouble(sTolerance);
        EditDrugDto editDrugDto =
                new EditDrugDto(drugId, name, dosage, drugTypeId, unitId, substance, tolerance,
                        isFavorite);
        editDrug.execute(editDrugDto);
    }

    /**
     * Create a new drug
     *
     * @param name       name of the drug
     * @param substance  substance of the drug
     * @param dosage     dosage of the drug
     * @param drugTypeId drug type id of the drug
     * @param unitId     unit id of the drug
     * @param sTolerance tolerance of the drug
     * @param isFavorite if the drug is a favorite
     */
    public void createDrug(
            String name,
            String substance,
            String dosage,
            int drugTypeId,
            int unitId,
            String sTolerance,
            boolean isFavorite) {
        double tolerance = tryParseDouble(sTolerance);
        CreateDrugDto createDrugDto =
                new CreateDrugDto(name, dosage, drugTypeId, unitId, substance, tolerance,
                        isFavorite);
        createDrug.execute(createDrugDto);
    }

    /**
     * Delete a drug by id.
     *
     * @param drugId id of the drug to be deleted
     */
    public void deleteDrug(int drugId) throws DrugstoreException {
        deleteDrug.execute(drugId);
    }
}
