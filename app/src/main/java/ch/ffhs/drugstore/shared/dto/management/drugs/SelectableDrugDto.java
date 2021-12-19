package ch.ffhs.drugstore.shared.dto.management.drugs;

/**
 * Data Transfer Object (DTO) class used to encapsulate- and transfer data between data- and domain
 * layer
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class SelectableDrugDto extends DrugDto {
    private boolean isSelected;

    public SelectableDrugDto(int drugId, String title, String drugType, String substance,
            String unit, String dosage, double tolerance, double stockAmount, boolean isFavorite,
            boolean isSelected) {
        super(drugId, title, drugType, substance, unit, dosage, tolerance, stockAmount, isFavorite);
        this.isSelected = isSelected;
    }

    public SelectableDrugDto(DrugDto drugDto, boolean isSelected) {
        super(drugDto.getDrugId(), drugDto.getTitle(), drugDto.getDrugType(),
                drugDto.getSubstance(), drugDto.getUnit(), drugDto.getDosage(),
                drugDto.getTolerance(), drugDto.getStockAmount(), drugDto.isFavorite());
        this.isSelected = isSelected;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
