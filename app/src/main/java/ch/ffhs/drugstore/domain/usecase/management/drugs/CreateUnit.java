package ch.ffhs.drugstore.domain.usecase.management.drugs;

import javax.inject.Inject;

import ch.ffhs.drugstore.domain.service.DrugManagementService;
import ch.ffhs.drugstore.domain.usecase.UseCase;

public class CreateUnit implements UseCase<Void, String> {
    private final DrugManagementService drugManagementService;

    @Inject
    public CreateUnit(DrugManagementService drugManagementService) {
        this.drugManagementService = drugManagementService;
    }

    @Override
    public Void execute(String unitTitle) {
        drugManagementService.createUnit(unitTitle);
        return null;
    }
}
