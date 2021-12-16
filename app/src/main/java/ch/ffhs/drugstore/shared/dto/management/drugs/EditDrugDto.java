package ch.ffhs.drugstore.shared.dto.management.drugs;

public class EditDrugDto {
    private int drugId;
    private String title;
    private String substance;
    private String dosage;
    private int drugTypeId;
    private int unitId;
    private double tolerance;
    private boolean isFavorite;

    public EditDrugDto(int drugId, String title, String dosage, int drugTypeId, int unitId,
            String substance,
            double tolerance, boolean isFavorite) {
        this.drugId = drugId;
        this.title = title;
        this.dosage = dosage;
        this.drugTypeId = drugTypeId;
        this.unitId = unitId;
        this.substance = substance;
        this.tolerance = tolerance;
        this.isFavorite = isFavorite;
    }

    public int getDrugId() {
        return drugId;
    }

    public void setDrugId(int drugId) {
        this.drugId = drugId;
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

    public String getSubstance() {
        return substance;
    }

    public void setSubstance(String substance) {
        this.substance = substance;
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
