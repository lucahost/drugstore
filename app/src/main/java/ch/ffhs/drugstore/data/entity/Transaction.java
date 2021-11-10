package ch.ffhs.drugstore.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Transaction {
    @PrimaryKey(autoGenerate = true)
    public long transactionId;
    public long userId;
    public long drugId;
    @ColumnInfo(defaultValue = "(datetime('now'))")
    public String createdAt;
    public double amount;
}
