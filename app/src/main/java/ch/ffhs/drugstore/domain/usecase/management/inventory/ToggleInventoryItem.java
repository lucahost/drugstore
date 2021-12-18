package ch.ffhs.drugstore.domain.usecase.management.inventory;

import javax.inject.Inject;

import ch.ffhs.drugstore.R;
import ch.ffhs.drugstore.domain.service.DrugManagementService;
import ch.ffhs.drugstore.domain.usecase.UseCase;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugDto;
import ch.ffhs.drugstore.shared.dto.management.signature.SignatureDrugDto;
import ch.ffhs.drugstore.shared.exceptions.DrugNotFoundException;
import ch.ffhs.drugstore.shared.exceptions.DrugstoreException;

public class ToggleInventoryItem implements UseCase<SignatureDrugDto, Integer> {
    @Inject
    DrugManagementService drugManagementService;

    @Inject
    public ToggleInventoryItem(DrugManagementService drugManagementService) {
        this.drugManagementService = drugManagementService;
    }

    @Override
    public SignatureDrugDto execute(Integer drugId) throws DrugstoreException {
        DrugDto drug = drugManagementService.getDrugById(drugId);
        if (drug == null) {
            throw new DrugNotFoundException(R.string.drug_not_found);
        }
        return new SignatureDrugDto(0, drugId, drug,
                drug.getStockAmount(), drug.getStockAmount(), true);
    }
}
