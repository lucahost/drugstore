package ch.ffhs.drugstore.data.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.time.ZonedDateTime;

/**
 * This class represents the Transaction entity in the local database to use with Room persistence
 * library
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
@Entity(
        tableName = "transactions",
        foreignKeys = {
                @ForeignKey(entity = User.class, parentColumns = "userId", childColumns = "userId"),
                @ForeignKey(entity = Drug.class, parentColumns = "drugId", childColumns = "drugId")
        },
        indices = {@Index(value = "userId"), @Index(value = "drugId")})
public class Transaction {
    @PrimaryKey(autoGenerate = true)
    private int transactionId;

    private int userId;
    private int drugId;
    private ZonedDateTime createdAt;
    private double amount;
    private String patient;

    public Transaction(
            int transactionId,
            int userId,
            int drugId,
            ZonedDateTime createdAt,
            double amount,
            String patient) {
        this.transactionId = transactionId;
        this.userId = userId;
        this.drugId = drugId;
        this.createdAt = createdAt;
        this.amount = amount;
        this.patient = patient;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getDrugId() {
        return drugId;
    }

    public void setDrugId(int drugId) {
        this.drugId = drugId;
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
