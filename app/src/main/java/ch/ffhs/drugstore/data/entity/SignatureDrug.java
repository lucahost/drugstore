package ch.ffhs.drugstore.data.entity;

import androidx.room.Entity;

@Entity(primaryKeys = {"signatureId", "drugId"})
public class SignatureDrug {
    public long signatureId;
    public long drugId;

    public double expectedAmount;
    public double actualAmount;
}
