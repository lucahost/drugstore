package ch.ffhs.drugstore.domain.dto.management.drugs;

public class CreateDrugDto {
    private String title;
    private String dosage;
    private int drugTypeId;
    private int unitId;
    private int substanceId;
    private double tolerance;
    private boolean isFavorite;

    public CreateDrugDto(String title, String dosage, int drugTypeId, int unitId, int substanceId,
            double tolerance, boolean isFavorite) {
        this.title = title;
        this.dosage = dosage;
        this.drugTypeId = drugTypeId;
        this.unitId = unitId;
        this.substanceId = substanceId;
        this.tolerance = tolerance;
        this.isFavorite = isFavorite;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public int getDrugTypeId() {
        return drugTypeId;
    }

    public void setDrugTypeId(int drugTypeId) {
        this.drugTypeId = drugTypeId;
    }

    public int getUnitId() {
        return unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }

    public int getSubstanceId() {
        return substanceId;
    }

    public void setSubstanceId(int substanceId) {
        this.substanceId = substanceId;
    }

    public double getTolerance() {
        return tolerance;
    }

    public void setTolerance(double tolerance) {
        this.tolerance = tolerance;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
