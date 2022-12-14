package ch.ffhs.drugstore.data.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

@Entity(tableName = "signatureDrugs",
        primaryKeys = {"signatureId", "drugId"},
        foreignKeys = {
                @ForeignKey(entity = Drug.class, parentColumns = "drugId", childColumns = "drugId"),
                @ForeignKey(entity = Signature.class, parentColumns = "signatureId",
                        childColumns = "signatureId")
        },
        indices = {
                @Index(value = {"signatureId", "drugId"}, unique = true),
                @Index(value = "drugId"),
                @Index(value = "signatureId")
        }
)
public class SignatureDrug {

    public int signatureId;
    public int drugId;
    public double expectedAmount;
    public double actualAmount;
    public boolean approved;

    public SignatureDrug(int signatureId, int drugId, double expectedAmount, double actualAmount,
            boolean approved) {
        this.signatureId = signatureId;
        this.drugId = drugId;
        this.expectedAmount = expectedAmount;
        this.actualAmount = actualAmount;
        this.approved = approved;
    }

    public int getSignatureId() {
        return signatureId;
    }

    public void setSignatureId(int signatureId) {
        this.signatureId = signatureId;
    }

    public int getDrugId() {
        return drugId;
    }

    public void setDrugId(int drugId) {
        this.drugId = drugId;
    }

    public double getExpectedAmount() {
        return expectedAmount;
    }

    public void setExpectedAmount(double expectedAmount) {
        this.expectedAmount = expectedAmount;
    }

    public double getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(double actualAmount) {
        this.actualAmount = actualAmount;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}
