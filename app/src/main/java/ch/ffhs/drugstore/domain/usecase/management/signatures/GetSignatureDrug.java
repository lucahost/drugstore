package ch.ffhs.drugstore.domain.usecase.management.signatures;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.domain.service.SignatureService;
import ch.ffhs.drugstore.domain.usecase.UseCase;
import ch.ffhs.drugstore.shared.dto.management.signature.SignatureDrugDto;

/**
 * Use-Case class to get the signature of a drug
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class GetSignatureDrug implements UseCase<LiveData<List<SignatureDrugDto>>, Integer> {
    private final SignatureService signatureService;

    /**
     * Construct a {@link GetSignatureDrug} use case
     *
     * @param signatureService signature service
     */
    @Inject
    public GetSignatureDrug(SignatureService signatureService) {
        this.signatureService = signatureService;
    }

    /**
     * {@inheritDoc}
     *
     * @param signatureId the id of the signature
     * @return live data list of signature drugs
     */
    @Override
    public LiveData<List<SignatureDrugDto>> execute(Integer signatureId) {
        return signatureService.getSignatureDrugsBySignatureId(signatureId);
    }
}
