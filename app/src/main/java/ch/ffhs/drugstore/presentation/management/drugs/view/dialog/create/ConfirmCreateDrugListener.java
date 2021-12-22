package ch.ffhs.drugstore.presentation.management.drugs.view.dialog.create;

/**
 * Confirmation listener interface for creating drugs
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public interface ConfirmCreateDrugListener {
    /**
     * Confirm edit of a drug
     *
     * @param name       the name of the drug
     * @param substance  the substance of the drug
     * @param dosage     the dosage of the drug
     * @param drugTypeId the drug type id of the drug
     * @param unitId     the unit id of the drug
     * @param tolerance  the tolerance of the drug
     * @param isFavorite if the drug is a favorite
     */
    void onConfirmCreateDrug(
            String name,
            String substance,
            String dosage,
            int drugTypeId,
            int unitId,
            String tolerance,
            boolean isFavorite);
}
