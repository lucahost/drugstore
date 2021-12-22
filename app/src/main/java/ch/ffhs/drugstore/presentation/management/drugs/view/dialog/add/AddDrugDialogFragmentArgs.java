package ch.ffhs.drugstore.presentation.management.drugs.view.dialog.add;

/**
 * Arguments to construct a {@link AddDrugDialogFragment}
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class AddDrugDialogFragmentArgs {
    private final int drugId;
    private final String drugTitle;
    private final String dosage;
    private final String unit;
    private final double stockAmount;

    /**
     * @param drugId      the id of the drug
     * @param drugTitle   the title of the drug
     * @param dosage      the dosage of the drug
     * @param unit        the unit of the drug
     * @param stockAmount the stock of the drug
     */
    public AddDrugDialogFragmentArgs(
            int drugId, String drugTitle, String dosage, String unit, double stockAmount) {
        this.drugId = drugId;
        this.drugTitle = drugTitle;
        this.dosage = dosage;
        this.unit = unit;
        this.stockAmount = stockAmount;
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
    public String getDosage() {
        return dosage;
    }

    /**
     * @return the unit of the drug
     */
    public String getUnit() {
        return unit;
    }

    /**
     * @return the stock of the drug
     */
    public double getStockAmount() {
        return stockAmount;
    }
}
