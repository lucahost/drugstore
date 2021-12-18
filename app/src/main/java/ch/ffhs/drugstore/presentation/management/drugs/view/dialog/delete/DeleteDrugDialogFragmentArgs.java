package ch.ffhs.drugstore.presentation.management.drugs.view.dialog.delete;

/**
 * Arguments to construct a {@link DeleteDrugDialogFragment}
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class DeleteDrugDialogFragmentArgs {
    private final int drugId;
    private final String drugTitle;

    /**
     * @param drugId    the id of the drug
     * @param drugTitle the title of the drug
     */
    public DeleteDrugDialogFragmentArgs(int drugId, String drugTitle) {
        this.drugId = drugId;
        this.drugTitle = drugTitle;
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
}
