package ch.ffhs.drugstore.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "signatures",
        foreignKeys = {
                @ForeignKey(entity = User.class, parentColumns = "userId", childColumns = "userId"),
        },
        indices = {
                @Index(value = "userId")
        })
public class Signature {
    @PrimaryKey(autoGenerate = true)
    public long signatureId;
    public long userId;
    @ColumnInfo(defaultValue = "(datetime('now'))")
    public Date createdAt;

    public Signature(long signatureId, long userId, Date createdAt) {
        this.signatureId = signatureId;
        this.userId = userId;
        this.createdAt = createdAt;
    }

    public long getSignatureId() {
        return signatureId;
    }

    public void setSignatureId(long signatureId) {
        this.signatureId = signatureId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
