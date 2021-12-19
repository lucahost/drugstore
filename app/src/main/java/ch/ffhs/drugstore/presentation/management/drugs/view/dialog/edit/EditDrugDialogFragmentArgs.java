package ch.ffhs.drugstore.presentation.management.drugs.view.dialog.edit;

/**
 * Arguments to construct a {@link EditDrugDialogFragment}
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class EditDrugDialogFragmentArgs {
    private final int drugId;
    private final String drugTitle;
    private final String substance;
    private final String dosage;
    private final String drugType;
    private final String drugUnit;
    private final double tolerance;
    private final boolean isFavorite;

    /**
     * @param drugId     the id of the drug
     * @param drugTitle  the title of the drug
     * @param substance  the substance of the drug
     * @param dosage     the dosage of the drug
     * @param drugType   the drugType of the drug
     * @param drugUnit   the unit of the drug
     * @param tolerance  the tolerance of the drug
     * @param isFavorite if the drug is a favorite
     */
    public EditDrugDialogFragmentArgs(int drugId, String drugTitle, String substance, String dosage,
            String drugType,
            String drugUnit, double tolerance, boolean isFavorite) {
        this.drugId = drugId;
        this.drugTitle = drugTitle;
        this.substance = substance;
        this.dosage = dosage;
        this.drugType = drugType;
        this.drugUnit = drugUnit;
        this.tolerance = tolerance;
        this.isFavorite = isFavorite;
    }

    /**
     * @return the id of the drug
     */
    public int getDrugId() {
        return drugId;
    }

    /**
     * @return the title of the drug
     */
    public String getDrugTitle() {
        return drugTitle;
    }

    /**
     * @return the substance of the drug
     */
    public String getSubstance() {
        return substance;
    }

    /**
     * @return the dosage of the drug
     */
    public String getDosage() {
        return dosage;
    }

    /**
     * @return the drugType of the drug
     */
    public String getDrugType() {
        return drugType;
    }

    /**
     * @return the unit of the drug
     */
    public String getDrugUnit() {
        return drugUnit;
    }

    /**
     * @return the tolerance of the drug
     */
    public double getTolerance() {
        return tolerance;
    }

    /**
     * @return if the drug is a favorite
     */
    public boolean isFavorite() {
        return isFavorite;
    }
}
