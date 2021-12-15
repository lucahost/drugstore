package ch.ffhs.drugstore.shared.dto.management.history;

import java.util.Date;

import ch.ffhs.drugstore.shared.dto.dispensary.SubmitDispenseDto;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugDto;
import ch.ffhs.drugstore.shared.dto.management.user.UserDto;

public class TransactionDto {
    private int transactionId;
    private DrugDto drug;
    private UserDto user;
    private Date createdAt;
    private double amount;
    private String patient;

    public TransactionDto() {

    }

    public TransactionDto(int transactionId, DrugDto drug, UserDto user, Date createdAt,
            double amount,
            String patient) {
        this.transactionId = transactionId;
        this.drug = drug;
        this.user = user;
        this.createdAt = createdAt;
        this.amount = amount;
        this.patient = patient;
    }

    public TransactionDto(SubmitDispenseDto submitDispenseDto, DrugDto drug) {
        this.drug = drug;
        this.amount = submitDispenseDto.getAmount();
        this.patient = submitDispenseDto.getPatient();
        this.user = new UserDto();
        user.setShortName(submitDispenseDto.getUserShortname());
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public DrugDto getDrug() {
        return drug;
    }

    public void setDrug(DrugDto drug) {
        this.drug = drug;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }
}
