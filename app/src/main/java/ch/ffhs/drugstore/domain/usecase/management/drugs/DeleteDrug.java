package ch.ffhs.drugstore.domain.usecase.management.drugs;

import javax.inject.Inject;

import ch.ffhs.drugstore.domain.service.DrugManagementService;
import ch.ffhs.drugstore.domain.usecase.UseCase;

/**
 * Use-Case class to delete a drug
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class DeleteDrug implements UseCase<Void, Integer> {
    private final DrugManagementService drugManagementService;

    /**
     * Construct a {@link DeleteDrug} use case
     *
     * @param drugManagementService drug management service
     */
    @Inject
    public DeleteDrug(DrugManagementService drugManagementService) {
        this.drugManagementService = drugManagementService;
    }

    /**
     * {@inheritDoc}
     *
     * @param drugId the id of the drug to delete
     * @return Void
     */
    @Override
    public Void execute(Integer drugId) {
        // TODO if drug is in transaction / history prevent delete
        drugManagementService.deleteDrug(drugId);
        return null;
    }
}
