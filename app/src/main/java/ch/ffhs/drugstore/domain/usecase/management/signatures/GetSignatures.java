package ch.ffhs.drugstore.domain.usecase.management.signatures;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.domain.service.SignatureService;
import ch.ffhs.drugstore.domain.usecase.UseCase;
import ch.ffhs.drugstore.shared.dto.management.signature.SignatureDto;

/**
 * Use-Case class to get a signature
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class GetSignatures implements UseCase<LiveData<List<SignatureDto>>, Void> {
    private final SignatureService signatureService;

    /**
     * Construct a {@link GetSignatures} use case
     *
     * @param signatureService signature service
     */
    @Inject
    public GetSignatures(SignatureService signatureService) {
        this.signatureService = signatureService;
    }

    /**
     * {@inheritDoc}
     *
     * @param unused no parameters
     * @return live data list of signatures
     */
    @Override
    public LiveData<List<SignatureDto>> execute(Void unused) {
        return signatureService.getSignatures();
    }
}
