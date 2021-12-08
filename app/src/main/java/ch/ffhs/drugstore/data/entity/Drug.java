package ch.ffhs.drugstore.data.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "drugs",
        foreignKeys = {
                @ForeignKey(entity = DrugType.class, parentColumns = "drugTypeId", childColumns =
                        "drugTypeId"),
                @ForeignKey(entity = Substance.class, parentColumns = "substanceId",
                        childColumns = "substanceId")
        },
        indices = {
                @Index(value = "drugTypeId"),
                @Index(value = "substanceId")
        }
)
public class Drug {
    @PrimaryKey(autoGenerate = true)
    private int drugId;
    private String title;
    private int drugTypeId;
    private int substanceId;
    private String dosage;
    private double tolerance;
    private double stockAmount;
    private boolean isFavorite;


    public Drug(int drugId, String title, int drugTypeId, int substanceId, String dosage,
            double tolerance,
            double stockAmount, boolean isFavorite) {
        this.drugId = drugId;
        this.title = title;
        this.drugTypeId = drugTypeId;
        this.substanceId = substanceId;
        this.dosage = dosage;
        this.tolerance = tolerance;
        this.stockAmount = stockAmount;
        this.isFavorite = isFavorite;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDrugId() {
        return drugId;
    }

    public void setDrugId(int drugId) {
        this.drugId = drugId;
    }

    public int getDrugTypeId() {
        return drugTypeId;
    }

    public void setDrugTypeId(int drugTypeId) {
        this.drugTypeId = drugTypeId;
    }

    public int getSubstanceId() {
        return substanceId;
    }

    public void setSubstanceId(int substanceId) {
        this.substanceId = substanceId;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public double getTolerance() {
        return tolerance;
    }

    public void setTolerance(double tolerance) {
        this.tolerance = tolerance;
    }

    public double getStockAmount() {
        return stockAmount;
    }

    public void setStockAmount(double stockAmount) {
        this.stockAmount = stockAmount;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
