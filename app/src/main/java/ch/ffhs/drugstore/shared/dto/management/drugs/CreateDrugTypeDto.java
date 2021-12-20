package ch.ffhs.drugstore.shared.dto.management.drugs;

public class CreateDrugTypeDto {
    private String drugTypeTitle;
    private String parentDrugTypeTitle;

    public CreateDrugTypeDto(String drugTypeTitle, String parentDrugTypeTitle) {
        this.drugTypeTitle = drugTypeTitle;
        this.parentDrugTypeTitle = parentDrugTypeTitle;
    }

    public String getDrugTypeTitle() {
        return drugTypeTitle;
    }

    public void setDrugTypeTitle(String drugTypeTitle) {
        this.drugTypeTitle = drugTypeTitle;
    }

    public String getParentDrugTypeTitle() {
        return parentDrugTypeTitle;
    }

    public void setParentDrugTypeTitle(String parentDrugTypeTitle) {
        this.parentDrugTypeTitle = parentDrugTypeTitle;
    }
}
