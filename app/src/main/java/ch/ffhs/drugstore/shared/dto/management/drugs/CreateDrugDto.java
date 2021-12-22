package ch.ffhs.drugstore.shared.dto.management.drugs;

/**
 * Data Transfer Object (DTO) class used to encapsulate- and transfer data between data- and domain
 * layer
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class CreateDrugDto {
    private final String title;
    private final String substance;
    private final String dosage;
    private final int drugTypeId;
    private final int unitId;
    private final double tolerance;
    private final boolean isFavorite;

    public CreateDrugDto(
            String title,
            String dosage,
            int drugTypeId,
            int unitId,
            String substance,
            double tolerance,
            boolean isFavorite) {
        this.title = title;
        this.dosage = dosage;
        this.drugTypeId = drugTypeId;
        this.unitId = unitId;
        this.substance = substance;
        this.tolerance = tolerance;
        this.isFavorite = isFavorite;
    }

    public String getTitle() {
        return title;
    }

    public String getDosage() {
        return dosage;
    }

    public int getDrugTypeId() {
        return drugTypeId;
    }

    public int getUnitId() {
        return unitId;
    }

    public String getSubstance() {
        return substance;
    }

    public double getTolerance() {
        return tolerance;
    }

    public boolean isFavorite() {
        return isFavorite;
    }
}
