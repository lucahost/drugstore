package ch.ffhs.drugstore.shared.dto.management.drugs;

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
