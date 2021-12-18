package ch.ffhs.drugstore.domain.usecase.management.inventory;

import javax.inject.Inject;

import ch.ffhs.drugstore.domain.service.SignatureService;
import ch.ffhs.drugstore.domain.usecase.UseCase;
import ch.ffhs.drugstore.shared.dto.management.signature.CreateSignatureDto;

/**
 * Use-Case class to sign the inventory after a review
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class SignInventory implements UseCase<Void, CreateSignatureDto> {
    private final SignatureService signatureService;

    /**
     * Construct a {@link SignInventory} use case
     *
     * @param signatureService inventory service
     */
    @Inject
    public SignInventory(SignatureService signatureService) {
        this.signatureService = signatureService;
    }

    /**
     * {@inheritDoc}
     *
     * @param createSignatureDto create signature input dto of the use case
     * @return Void
     */
    @Override
    public Void execute(CreateSignatureDto createSignatureDto) {
        signatureService.createSignature(createSignatureDto);
        return null;
    }
}
