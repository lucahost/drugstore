package ch.ffhs.drugstore.shared.dto.management.signature;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * TODO: add description
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class CreateSignatureDto {
    List<SignatureDrugDto> signatureDrugs;
    private String userShortName;
    private ZonedDateTime createdAt;

    public CreateSignatureDto(String userShortName,
            List<SignatureDrugDto> signatureDrugs) {
        this.userShortName = userShortName;
        this.signatureDrugs = signatureDrugs;
        this.createdAt = ZonedDateTime.now(ZoneId.of("UTC"));
    }

    public String getUserShortName() {
        return userShortName;
    }

    public void setUserShortName(String userShortName) {
        this.userShortName = userShortName;
    }

    public List<SignatureDrugDto> getSignatureDrugs() {
        return signatureDrugs;
    }

    public void setSignatureDrugs(
            List<SignatureDrugDto> signatureDrugs) {
        this.signatureDrugs = signatureDrugs;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
