package ch.ffhs.drugstore.domain.usecase.management.drugs;

import javax.inject.Inject;

import ch.ffhs.drugstore.domain.service.DrugManagementService;
import ch.ffhs.drugstore.domain.usecase.UseCase;
import ch.ffhs.drugstore.shared.dto.management.drugs.EditDrugDto;
import ch.ffhs.drugstore.shared.exceptions.DrugstoreException;

/**
 * Use-Case class to edit a drug
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class EditDrug implements UseCase<Void, EditDrugDto> {
    private final DrugManagementService drugManagementService;

    /**
     * Construct a {@link EditDrug} use case
     *
     * @param drugManagementService drug management service
     */
    @Inject
    public EditDrug(DrugManagementService drugManagementService) {
        this.drugManagementService = drugManagementService;
    }

    /**
     * {@inheritDoc}
     *
     * @param editDrugDto edit drug input dto of the use case
     * @return Void
     * @throws DrugstoreException if creation of the drug goes wrong
     */

    @Override
    public Void execute(EditDrugDto editDrugDto) throws DrugstoreException {
        drugManagementService.editDrug(editDrugDto);
        return null;
    }
}
