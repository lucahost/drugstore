package ch.ffhs.drugstore.data.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Drug {
    @PrimaryKey(autoGenerate = true)
    public long drugId;
    public String title;
    public String dosage;
    public double tolerance;
    public boolean isFavorite;
    public int stockCount;
}
