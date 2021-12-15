package ch.ffhs.drugstore.shared.dto.management.drugs;

public class UpdateDrugAmountDto {
    private int drugId;
    private double amount;

    public UpdateDrugAmountDto(int drugId, double amount) {
        this.drugId = drugId;
        this.amount = amount;
    }

    public int getDrugId() {
        return drugId;
    }

    public void setDrugId(int drugId) {
        this.drugId = drugId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
