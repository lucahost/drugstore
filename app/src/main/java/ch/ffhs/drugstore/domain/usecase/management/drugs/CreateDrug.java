package ch.ffhs.drugstore.domain.usecase.management.drugs;

import javax.inject.Inject;

import ch.ffhs.drugstore.domain.service.DrugManagementService;
import ch.ffhs.drugstore.domain.usecase.UseCase;
import ch.ffhs.drugstore.shared.dto.management.drugs.CreateDrugDto;

/**
 * Use-Case class to create a new drug
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class CreateDrug implements UseCase<Void, CreateDrugDto> {
    private final DrugManagementService drugManagementService;

    /**
     * Construct a {@link CreateDrug} use case
     *
     * @param drugManagementService drug management service
     */
    @Inject
    public CreateDrug(DrugManagementService drugManagementService) {
        this.drugManagementService = drugManagementService;
    }

    /**
     * {@inheritDoc}
     *
     * @param createDrugDto create drug input dto of the use case
     * @return Void
     */
    @Override
    public Void execute(CreateDrugDto createDrugDto) {
        drugManagementService.createDrug(createDrugDto);
        return null;
    }
}
