package ch.ffhs.drugstore.presentation.management.drugs.view.dialog;

public class RemoveDrugDialogFragmentArgs {
    private final int drugId;
    private final String drugTitle;

    public RemoveDrugDialogFragmentArgs(int drugId, String drugTitle) {
        this.drugId = drugId;
        this.drugTitle = drugTitle;
    }

    public int getDrugId() {
        return drugId;
    }

    public String getDrugTitle() {
        return drugTitle;
    }
}
