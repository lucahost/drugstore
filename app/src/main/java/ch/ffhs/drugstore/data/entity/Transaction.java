package ch.ffhs.drugstore.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class Transaction {
  @PrimaryKey(autoGenerate = true)
  public long transactionId;

  public long userId;
  public long drugId;

  @ColumnInfo(defaultValue = "(datetime('now'))")
  public Date createdAt;

  public double amount;

  public Transaction(long transactionId, long userId, long drugId, Date createdAt, double amount) {
    this.transactionId = transactionId;
    this.userId = userId;
    this.drugId = drugId;
    this.createdAt = createdAt;
    this.amount = amount;
  }

  public long getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(long transactionId) {
    this.transactionId = transactionId;
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public long getDrugId() {
    return drugId;
  }

  public void setDrugId(long drugId) {
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
}
