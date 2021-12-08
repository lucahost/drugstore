package ch.ffhs.drugstore.data.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "substances")
public class Substance {
    @PrimaryKey(autoGenerate = true)
    private int substanceId;
    private String title;

    public Substance(int substanceId, String title) {
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
