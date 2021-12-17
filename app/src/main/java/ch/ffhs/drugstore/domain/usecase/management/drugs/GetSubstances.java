package ch.ffhs.drugstore.domain.usecase.management.drugs;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.domain.service.DrugManagementService;
import ch.ffhs.drugstore.domain.usecase.UseCase;
import ch.ffhs.drugstore.shared.dto.management.drugs.SubstanceDto;
import ch.ffhs.drugstore.shared.dto.management.drugs.UnitDto;

public class GetSubstances implements UseCase<LiveData<List<SubstanceDto>>, Void> {
    @Inject
    DrugManagementService drugManagementService;

    @Inject
    public GetSubstances(DrugManagementService drugManagementService) {
        this.drugManagementService = drugManagementService;
    }

    @Override
    public LiveData<List<SubstanceDto>> execute(Void params) {
        return drugManagementService.getAllSubstances();
    }
}
