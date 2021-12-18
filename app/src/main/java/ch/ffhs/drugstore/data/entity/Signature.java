package ch.ffhs.drugstore.data.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.time.ZonedDateTime;
/**
 * This class represents the Signature entity in the local database
 * to use with Room persistence library
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
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
    public ZonedDateTime createdAt;

    public Signature(long signatureId, long userId, ZonedDateTime createdAt) {
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

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
