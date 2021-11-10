package ch.ffhs.drugstore.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import java.util.List;

@Entity
public class Signature {
    @PrimaryKey(autoGenerate = true)
    public long signatureId;
    public long userId;
    @ColumnInfo(defaultValue = "(datetime('now'))")
    public String createdAt;
}
