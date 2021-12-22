package ch.ffhs.drugstore.data.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * This class represents the Unit entity in the local database to use with Room persistence library
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
@Entity(tableName = "units")
public class Unit {
    @PrimaryKey(autoGenerate = true)
    private int unitId;

    private String title;

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
