package ch.ffhs.drugstore.shared.dto.management.drugs;

public class SubstanceDto {
    private int substanceId;
    private String title;

    public SubstanceDto(int substanceId, String title) {
        this.substanceId = substanceId;
        this.title = title;
    }

    public int getSubstanceId() {
        return substanceId;
    }

    public void setSubstanceId(int substanceId) {
        this.substanceId = substanceId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
