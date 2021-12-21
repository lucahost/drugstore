package ch.ffhs.drugstore.shared.dto.management.history;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import ch.ffhs.drugstore.shared.dto.dispensary.SubmitDispenseDto;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugDto;
import ch.ffhs.drugstore.shared.dto.management.user.UserDto;

/**
 * TODO: add description
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class TransactionDto {
    private Integer transactionId;
    private DrugDto drug;
    private UserDto user;
    private ZonedDateTime createdAt;
    private double amount;
    private String patient;

    public TransactionDto() {

    }

    public TransactionDto(Integer transactionId, DrugDto drug, UserDto user, ZonedDateTime createdAt,
            double amount, String patient) {
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
        this.createdAt = ZonedDateTime.now();
        user.setShortName(submitDispenseDto.getUserShortname());
    }

    public Integer getTransactionId() {
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

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
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
