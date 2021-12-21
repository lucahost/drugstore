package ch.ffhs.drugstore.domain.usecase.management.drugTypes;

import javax.inject.Inject;

import ch.ffhs.drugstore.domain.service.DrugManagementService;
import ch.ffhs.drugstore.domain.usecase.UseCase;
import ch.ffhs.drugstore.shared.dto.management.drugs.CreateDrugTypeDto;

public class CreateDrugType implements UseCase<Void, CreateDrugTypeDto> {
    private final DrugManagementService drugManagementService;

    @Inject
    public CreateDrugType(DrugManagementService drugManagementService) {
        this.drugManagementService = drugManagementService;
    }

    @Override
    public Void execute(CreateDrugTypeDto createDrugTypeDto) {
        drugManagementService.createDrugType(createDrugTypeDto);
        return null;
    }
}
