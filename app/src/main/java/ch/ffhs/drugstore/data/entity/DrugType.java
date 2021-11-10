package ch.ffhs.drugstore.data.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class DrugType {
    @PrimaryKey(autoGenerate = true)
    public long drugTypeId;
    public String title;
}
