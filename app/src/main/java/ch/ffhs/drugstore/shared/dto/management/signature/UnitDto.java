package ch.ffhs.drugstore.data.dto;

public class UnitDto {
    private int unitId;
    private String title;

    public UnitDto(int unitId, String title) {
        this.unitId = unitId;
        this.title = title;
    }

    public int getUnitId() {
        return unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
