package ch.ffhs.drugstore.shared.dto.management.drugs;

/**
 * Data Transfer Object (DTO) class used to encapsulate- and transfer data between data- and domain
 * layer
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class DrugTypeDto {
    private int drugTypeId;
    private String title;

    public DrugTypeDto(int drugTypeId, String title) {
        this.drugTypeId = drugTypeId;
        this.title = title;
    }

    public int getDrugTypeId() {
        return drugTypeId;
    }

    public void setDrugTypeId(int drugTypeId) {
        this.drugTypeId = drugTypeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
