package ch.ffhs.drugstore.domain.usecase.management.drugs;

import javax.inject.Inject;

import ch.ffhs.drugstore.domain.service.DrugManagementService;
import ch.ffhs.drugstore.domain.usecase.UseCase;
/**
 * Use-Case class to delete a drug
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class DeleteDrug implements UseCase<Void, Integer> {
    @Inject
    DrugManagementService drugManagementService;

    @Inject
    public DeleteDrug(DrugManagementService drugManagementService) {
        this.drugManagementService = drugManagementService;
    }

    /**
     * method to delete drug with ID
     * @param drugId ID
     */
    @Override
    public Void execute(Integer drugId) {
        // TODO if drug is in transaction / history prevent delete
        drugManagementService.deleteDrug(drugId);
        return null;
    }
}
