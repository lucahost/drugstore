package ch.ffhs.drugstore.data.entity;

public class Drug {

    public enum DrugType {
        INJECTION, PILL, ORAL, ORAL_LIQUID, PLASTER
    }

    private int id;
    private String title;
    private DrugType type;
    private String dosage;
    private boolean isFavorite;

    public Drug(int id, String title, DrugType type, String dosage, boolean isFavorite) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.dosage = dosage;
        this.isFavorite = isFavorite;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DrugType getType() {
        return type;
    }

    public void setType(DrugType type) {
        this.type = type;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
