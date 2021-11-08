package ch.ffhs.drugstore.data.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Signature {
    @PrimaryKey(autoGenerate = true)
    public long signatureId;
}
