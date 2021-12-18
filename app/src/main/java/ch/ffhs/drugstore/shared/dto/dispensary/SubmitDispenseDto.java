package ch.ffhs.drugstore.shared.dto.dispensary;

/**
 * TODO: add description
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class SubmitDispenseDto {
    private int drugId;
    private String userShortname;
    private String patient;
    private double amount;


    public SubmitDispenseDto(int drugId, String userShortname, String patient, double amount) {
        this.drugId = drugId;
        this.userShortname = userShortname;
        this.patient = patient;
        this.amount = amount;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
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

    public String getUserShortname() {
        return userShortname;
    }

    public void setUserShortname(String userShortname) {
        this.userShortname = userShortname;
    }
}
