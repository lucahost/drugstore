package ch.ffhs.drugstore.data.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Drug {

    public enum DrugType {
        INJECTION, PILL, ORAL, ORAL_LIQUID, PLASTER
    }

    @PrimaryKey
    private int drugId;
    private String title;
    private DrugType type;
    private String dosage;
    private boolean isFavorite;

    public Drug(int drugId, String title, DrugType type, String dosage, boolean isFavorite) {
        this.drugId = drugId;
        this.title = title;
        this.type = type;
        this.dosage = dosage;
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

    public DrugType getType() {
        return type;
    }

    public void setType(DrugType type) {
        this.type = type;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
