package ch.ffhs.drugstore.presentation.management.drugs.view.dialog;

public class AddDrugDialogFragmentArgs {
    private final int drugId;
    private final String drugTitle;
    private final String dosage;
    private final String unit;

    public AddDrugDialogFragmentArgs(int drugId, String drugTitle, String dosage,
            String unit) {
        this.drugId = drugId;
        this.drugTitle = drugTitle;
        this.dosage = dosage;
        this.unit = unit;
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

    public String getUnit() {
        return unit;
    }
}
