package ch.ffhs.drugstore.domain.usecase.management.units;

import javax.inject.Inject;

import ch.ffhs.drugstore.domain.service.DrugManagementService;
import ch.ffhs.drugstore.domain.usecase.UseCase;

public class DeleteUnit implements UseCase<Void, Integer> {
    private final DrugManagementService drugManagementService;

    @Inject
    public DeleteUnit(DrugManagementService drugManagementService) {
        this.drugManagementService = drugManagementService;
    }

    @Override
    public Void execute(Integer unitId) {
        drugManagementService.deleteUnit(unitId);
        return null;
    }
}
