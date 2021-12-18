package ch.ffhs.drugstore.domain.usecase.management.inventory;

import javax.inject.Inject;

import ch.ffhs.drugstore.domain.service.DrugManagementService;
import ch.ffhs.drugstore.domain.service.SignatureService;
import ch.ffhs.drugstore.domain.usecase.UseCase;
import ch.ffhs.drugstore.shared.dto.management.signature.CreateSignatureDto;

public class SignInventory implements UseCase<Void, CreateSignatureDto> {

    @Inject
    SignatureService signatureService;

    @Inject
    public SignInventory(SignatureService signatureService) {
        this.signatureService = signatureService;
    }

    @Override
    public Void execute(CreateSignatureDto createSignatureDto) {
        signatureService.createSignature(createSignatureDto);
        return null;
    }
}
