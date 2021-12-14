package ch.ffhs.drugstore.data.dto;

import java.util.Date;

public class SignatureDto {
    private int signatureId;
    private UserDto user;
    private Date createdAt;

    public SignatureDto(int signatureId, UserDto user, Date createdAt) {
        this.signatureId = signatureId;
        this.user = user;
        this.createdAt = createdAt;
    }

    public int getSignatureId() {
        return signatureId;
    }

    public void setSignatureId(int signatureId) {
        this.signatureId = signatureId;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
