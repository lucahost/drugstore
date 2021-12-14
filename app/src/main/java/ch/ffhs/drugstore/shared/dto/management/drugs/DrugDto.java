package ch.ffhs.drugstore.shared.dto.management.drugs;

public class DrugDto {
    private int drugId;
    private String title;
    private String drugType;
    private String substance;
    private String unit;
    private String dosage;
    private double tolerance;
    private double stockAmount;
    private boolean isFavorite;

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public DrugDto(int drugId, String title, String drugType, String substance, String unit, String dosage,
            double tolerance,
            double stockAmount, boolean isFavorite) {
        this.drugId = drugId;
        this.title = title;
        this.drugType = drugType;
        this.substance = substance;
        this.unit = unit;
        this.dosage = dosage;
        this.tolerance = tolerance;
        this.stockAmount = stockAmount;
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

    public String getDrugType() {
        return drugType;
    }

    public void setDrugType(String drugType) {
        this.drugType = drugType;
    }

    public String getSubstance() {
        return substance;
    }

    public void setSubstance(String substance) {
        this.substance = substance;
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
