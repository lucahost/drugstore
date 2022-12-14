package ch.ffhs.drugstore.data.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

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
    public int parentDrugTypeId;
    public String title;

    public DrugType(int drugTypeId, int parentDrugTypeId, String title) {
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

    public int getParentDrugTypeId() {
        return parentDrugTypeId;
    }

    public void setParentDrugTypeId(int parentDrugTypeId) {
        this.parentDrugTypeId = parentDrugTypeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
