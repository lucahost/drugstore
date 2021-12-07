package ch.ffhs.drugstore.data.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class Signature {
  @PrimaryKey(autoGenerate = true)
  public long signatureId;

  public long userId;
  // TODO: does this work with type converte to Long?
  // @ColumnInfo(defaultValue = "(datetime('now'))")
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
