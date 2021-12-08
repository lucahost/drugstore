package ch.ffhs.drugstore.data.dto;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.Date;
import java.util.List;

import ch.ffhs.drugstore.data.entity.SignatureDrug;

public class SignatureDto {
    private int signatureId;
    @Embedded
    private UserDto user;
    @Relation(parentColumn = "signatureId", entityColumn = "signatureId", entity =
            SignatureDrug.class)
    private List<SignatureDrugDto> signatureDrugs;
    private Date createdAt;

    public SignatureDto() {
    }

    public SignatureDto(int signatureId, UserDto user, List<SignatureDrugDto> signatureDrugs,
            Date createdAt) {
        this.signatureId = signatureId;
        this.user = user;
        this.signatureDrugs = signatureDrugs;
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

    public List<SignatureDrugDto> getDrugs() {
        return signatureDrugs;
    }

    public void setDrugs(List<SignatureDrugDto> drugs) {
        this.signatureDrugs = drugs;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
