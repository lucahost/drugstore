package ch.ffhs.drugstore.presentation.management.drugs.view.dialog.delete;

/**
 * Confirmation listener interface for deleting drugs
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public interface ConfirmDeleteDrugListener {
    /**
     * Confirm deletion of a drug
     *
     * @param drugId the id of the drug
     */
    void onConfirmDeleteDrug(int drugId);
}
