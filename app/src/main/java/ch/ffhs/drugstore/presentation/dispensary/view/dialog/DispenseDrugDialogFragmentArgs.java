package ch.ffhs.drugstore.presentation.dispensary.view.dialog;

/**
 * Arguments to construct a {@link DispenseDrugDialogFragment}
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class DispenseDrugDialogFragmentArgs {
    private final int drugId;
    private final String drugTitle;
    private final String drugDosage;
    private final String drugUnit;

    /**
     * @param drugId     the id of the drug
     * @param drugTitle  the title of the drug
     * @param drugDosage the dosage of the drug
     * @param drugUnit   the unit of the drug
     */
    public DispenseDrugDialogFragmentArgs(
            int drugId, String drugTitle, String drugDosage, String drugUnit) {
        this.drugId = drugId;
        this.drugTitle = drugTitle;
        this.drugDosage = drugDosage;
        this.drugUnit = drugUnit;
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
     * @return the dosage of the drug
     */
    public String getDrugDosage() {
        return drugDosage;
    }

    /**
     * @return the unit of the drug
     */
    public String getDrugUnit() {
        return drugUnit;
    }
}
