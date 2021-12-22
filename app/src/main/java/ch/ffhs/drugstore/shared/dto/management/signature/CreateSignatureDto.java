package ch.ffhs.drugstore.shared.dto.management.signature;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * DTO to create a signature drug
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class CreateSignatureDto {
    private final String userShortName;
    private final ZonedDateTime createdAt;
    private final List<SignatureDrugDto> signatureDrugs;

    public CreateSignatureDto(String userShortName, List<SignatureDrugDto> signatureDrugs) {
        this.userShortName = userShortName;
        this.signatureDrugs = signatureDrugs;
        this.createdAt = ZonedDateTime.now(ZoneId.of("UTC"));
    }

    public String getUserShortName() {
        return userShortName;
    }

    public List<SignatureDrugDto> getSignatureDrugs() {
        return signatureDrugs;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }
}
