package ch.ffhs.drugstore.domain.usecase.management.drugTypes;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.domain.service.DrugManagementService;
import ch.ffhs.drugstore.domain.usecase.UseCase;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugTypeDto;

/**
 * Use-Case class to return all drug types
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class GetDrugTypes implements UseCase<LiveData<List<DrugTypeDto>>, Void> {
    private final DrugManagementService drugManagementService;

    /**
     * Construct a {@link GetDrugTypes} use case
     *
     * @param drugManagementService drug management service
     */
    @Inject
    public GetDrugTypes(DrugManagementService drugManagementService) {
        this.drugManagementService = drugManagementService;
    }

    /**
     * {@inheritDoc}
     *
     * @param unused no parameters
     * @return live data list of drug types
     */
    @Override
    public LiveData<List<DrugTypeDto>> execute(Void unused) {
        return drugManagementService.getAllDrugTypes();
    }
}
