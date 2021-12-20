package ch.ffhs.drugstore.shared.dto.management.drugs;

/**
 * Data Transfer Object (DTO) class used to encapsulate- and transfer data between data- and domain
 * layer
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class UnitDto {
    private Integer unitId;
    private String title;

    public UnitDto(Integer unitId, String title) {
        this.unitId = unitId;
        this.title = title;
    }

    public Integer getUnitId() {
        return unitId;
    }

    public void setUnitId(Integer unitId) {
        this.unitId = unitId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
