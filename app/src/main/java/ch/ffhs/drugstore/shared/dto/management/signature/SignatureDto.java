package ch.ffhs.drugstore.shared.dto.management.signature;

import java.time.ZonedDateTime;
import java.util.List;

import ch.ffhs.drugstore.shared.dto.management.user.UserDto;

/**
 * DTO to represent a signature
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class SignatureDto {
    private int signatureId;
    private UserDto user;
    private ZonedDateTime createdAt;
    private List<SignatureDrugDto> signatureDrugs;

    public SignatureDto(int signatureId, UserDto user, ZonedDateTime createdAt) {
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

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<SignatureDrugDto> getSignatureDrugs() {
        return signatureDrugs;
    }

    public void setSignatureDrugs(List<SignatureDrugDto> signatureDrugs) {
        this.signatureDrugs = signatureDrugs;
    }
}
