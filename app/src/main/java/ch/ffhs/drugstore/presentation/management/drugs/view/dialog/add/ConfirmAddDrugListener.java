package ch.ffhs.drugstore.presentation.management.drugs.view.dialog.add;

/**
 * Confirmation listener interface for adding drugs
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public interface ConfirmAddDrugListener {
    /**
     * Confirm addition of drug
     *
     * @param drugId the id of the drug
     * @param amount the amount to be added
     */
    void onConfirmAddDrug(int drugId, String amount);
}
