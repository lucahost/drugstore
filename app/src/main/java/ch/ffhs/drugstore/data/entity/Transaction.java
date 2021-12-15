package ch.ffhs.drugstore.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "transactions",
        foreignKeys = {
                @ForeignKey(entity = User.class, parentColumns = "userId", childColumns = "userId"),
                @ForeignKey(entity = Drug.class, parentColumns = "drugId", childColumns = "drugId")
        },
        indices = {
                @Index(value = "userId"),
                @Index(value = "drugId")
        }
)
public class Transaction {
    @PrimaryKey(autoGenerate = true)
    public int transactionId;
    public int userId;
    public int drugId;
    @ColumnInfo(defaultValue = "(datetime('now'))")
    public Date createdAt;
    public double amount;
    public String patient;

    public Transaction(int transactionId, int userId, int drugId, Date createdAt, double amount,
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
