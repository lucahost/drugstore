package ch.ffhs.drugstore.data.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
/**
 * This class represents the DrugType entity in the local database
 * to use with Room persistence library
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
@Entity(tableName = "drugTypes",
        foreignKeys = {
                @ForeignKey(entity = DrugType.class, parentColumns = "drugTypeId", childColumns =
                        "parentDrugTypeId")
        },
        indices = {
                @Index(value = "parentDrugTypeId")
        }
)
public class DrugType {
    @PrimaryKey(autoGenerate = true)
    public int drugTypeId;
    public Integer parentDrugTypeId;
    public String title;

    public DrugType(int drugTypeId, Integer parentDrugTypeId, String title) {
        this.drugTypeId = drugTypeId;
        this.parentDrugTypeId = parentDrugTypeId;
        this.title = title;
    }

    public int getDrugTypeId() {
        return drugTypeId;
    }

    public void setDrugTypeId(int drugTypeId) {
        this.drugTypeId = drugTypeId;
    }

    public Integer getParentDrugTypeId() {
        return parentDrugTypeId;
    }

    public void setParentDrugTypeId(Integer parentDrugTypeId) {
        this.parentDrugTypeId = parentDrugTypeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
