package ch.ffhs.drugstore.data.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * This class represents the Drug entity in the local database to use with Room persistence library
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
@Entity(tableName = "drugs",
        foreignKeys = {
                @ForeignKey(entity = DrugType.class, parentColumns = "drugTypeId", childColumns =
                        "drugTypeId"),
                @ForeignKey(entity = Substance.class, parentColumns = "substanceId",
                        childColumns = "substanceId"),
                @ForeignKey(entity = Unit.class, parentColumns = "unitId",
                        childColumns = "unitId")
        },
        indices = {
                @Index(value = "drugTypeId"),
                @Index(value = "substanceId"),
                @Index(value = "unitId")
        }
)
public class Drug {
    @PrimaryKey(autoGenerate = true)
    private int drugId;
    private String title;
    private Integer drugTypeId;
    private Integer substanceId;
    private Integer unitId;
    private String dosage;
    private double tolerance;
    private double stockAmount;
    private boolean isFavorite;


    /**
     * Constructs a {@link Drug}.
     */
    public Drug(int drugId, String title, Integer drugTypeId, Integer substanceId, Integer unitId,
            String dosage, double tolerance, double stockAmount, boolean isFavorite) {
        this.drugId = drugId;
        this.title = title;
        this.drugTypeId = drugTypeId;
        this.substanceId = substanceId;
        this.unitId = unitId;
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

    public Integer getDrugTypeId() {
        return drugTypeId;
    }

    public void setDrugTypeId(Integer drugTypeId) {
        this.drugTypeId = drugTypeId;
    }

    public Integer getSubstanceId() {
        return substanceId;
    }

    public void setSubstanceId(Integer substanceId) {
        this.substanceId = substanceId;
    }

    public Integer getUnitId() {
        return unitId;
    }

    public void setUnitId(Integer unitId) {
        this.unitId = unitId;
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
