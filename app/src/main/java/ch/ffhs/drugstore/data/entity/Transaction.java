package ch.ffhs.drugstore.data.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Transaction {
    @PrimaryKey(autoGenerate = true)
    public long transactionId;
}
