package ch.ffhs.drugstore.domain.usecase.management.drugTypes;

import javax.inject.Inject;

import ch.ffhs.drugstore.domain.service.DrugManagementService;
import ch.ffhs.drugstore.domain.usecase.UseCase;

public class DeleteDrugType implements UseCase<Void, Integer> {
    private final DrugManagementService drugManagementService;

    @Inject
    public DeleteDrugType(DrugManagementService drugManagementService) {
        this.drugManagementService = drugManagementService;
    }

    @Override
    public Void execute(Integer drugTypeId) {
        drugManagementService.deleteDrugType(drugTypeId);
        return null;
    }
}