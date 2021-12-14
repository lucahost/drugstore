package ch.ffhs.drugstore.presentation.management.drugs.view.dialog;

public class EditDrugDialogFragmentArgs {
    private final int drugId;
    private final String drugTitle;
    private final String dosage;
    private final String drugType;
    private final double tolerance;
    private final boolean isFavorite;

    public EditDrugDialogFragmentArgs(int drugId, String drugTitle, String dosage, String drugType, double tolerance, boolean isFavorite) {
        this.drugId = drugId;
        this.drugTitle = drugTitle;
        this.dosage = dosage;
        this.drugType = drugType;
        this.tolerance = tolerance;
        this.isFavorite = isFavorite;
    }

    public int getDrugId() {
        return drugId;
    }

    public String getDrugTitle() {
        return drugTitle;
    }

    public String getDosage() {
        return dosage;
    }

    public String getDrugType() {
        return drugType;
    }

    public double getTolerance() {
        return tolerance;
    }

    public boolean isFavorite() {
        return isFavorite;
    }
}
