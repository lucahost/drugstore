package ch.ffhs.drugstore.data.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "units")
public class Unit {
    @PrimaryKey(autoGenerate = true)
    public int unitId;
    public String title;

    public Unit(int unitId, String title) {
        this.unitId = unitId;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUnitId() {
        return unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }
}
