package ch.ffhs.drugstore.shared.dto.management.drugs;

/**
 * Data Transfer Object (DTO) class used to encapsulate- and transfer data between data- and domain
 * layer
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class UpdateDrugAmountDto {
    private int drugId;
    private double amount;
    private String userShortName;

    public UpdateDrugAmountDto(int drugId, double amount, String userShortName) {
        this.drugId = drugId;
        this.amount = amount;
        this.userShortName = userShortName;
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

    public String getUserShortName() {
        return userShortName;
    }

    public void setUserShortName(String userShortName) {
        this.userShortName = userShortName;
    }
}
