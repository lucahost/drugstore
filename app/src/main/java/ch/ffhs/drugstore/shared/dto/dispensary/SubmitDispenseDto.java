package ch.ffhs.drugstore.shared.dto.dispensary;

public class SubmitDispenseDto {
    private int drugId;
    private String userId;
    private String patient;
    private double amount;


    public SubmitDispenseDto(int drugId, String userId, String patient, double amount) {
        this.drugId = drugId;
        this.userId = userId;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
