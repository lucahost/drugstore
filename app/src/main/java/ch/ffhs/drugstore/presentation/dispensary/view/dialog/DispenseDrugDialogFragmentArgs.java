package ch.ffhs.drugstore.presentation.dispensary.view.dialog;

public class DispenseDrugDialogFragmentArgs {
    private final int drugId;
    private final String drugTitle;
    private final String drugDosage;

    public DispenseDrugDialogFragmentArgs(int drugId, String drugTitle, String drugDosage) {
        this.drugId = drugId;
        this.drugTitle = drugTitle;
        this.drugDosage = drugDosage;
    }

    public int getDrugId() {
        return drugId;
    }

    public String getDrugTitle() {
        return drugTitle;
    }

    public String getDrugDosage() {
        return drugDosage;
    }
}
