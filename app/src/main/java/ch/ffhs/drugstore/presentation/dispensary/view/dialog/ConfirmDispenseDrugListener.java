package ch.ffhs.drugstore.presentation.dispensary.view.dialog;

/**
 * Confirmation listener interface for drug dispensation
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public interface ConfirmDispenseDrugListener {
    /**
     * Confirm drug dispensation
     *
     * @param drugId   the id of the drug
     * @param employee the employee dispensing the drug
     * @param patient  the patient receiving the drug
     * @param dosage   the dosage to be dispensed
     */
    void onConfirmDispenseDrug(int drugId, String employee, String patient, String dosage);
}
