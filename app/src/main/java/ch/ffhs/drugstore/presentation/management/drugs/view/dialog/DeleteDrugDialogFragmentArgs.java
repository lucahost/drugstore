package ch.ffhs.drugstore.presentation.management.drugs.view.dialog;

public class DeleteDrugDialogFragmentArgs {
    private final int drugId;
    private final String drugTitle;

    public DeleteDrugDialogFragmentArgs(int drugId, String drugTitle) {
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
