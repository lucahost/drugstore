package ch.ffhs.drugstore.presentation.management.drugs.view.dialog.remove;

/**
 * Confirmation listener interface for removing drugs
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public interface ConfirmRemoveDrugListener {
    /**
     * Confirm removal of a drug
     *
     * @param drugId the id of the drug
     * @param amount the amount to be removed
     * @param userShortName the shortName of the currentUser
     */
    void onConfirmRemoveDrug(int drugId, String amount, String userShortName);
}
