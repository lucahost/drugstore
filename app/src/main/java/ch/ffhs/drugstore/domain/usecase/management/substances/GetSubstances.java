package ch.ffhs.drugstore.domain.usecase.management.substances;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.domain.service.DrugManagementService;
import ch.ffhs.drugstore.domain.usecase.UseCase;
import ch.ffhs.drugstore.shared.dto.management.drugs.SubstanceDto;

/**
 * Use-Case class to get all substances
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class GetSubstances implements UseCase<LiveData<List<SubstanceDto>>, Void> {
    private final DrugManagementService drugManagementService;

    /**
     * Construct a {@link GetSubstances} use case
     *
     * @param drugManagementService drug management service
     */
    @Inject
    public GetSubstances(DrugManagementService drugManagementService) {
        this.drugManagementService = drugManagementService;
    }

    /**
     * {@inheritDoc}
     *
     * @param unused no parameters
     * @return live data list of substances
     */
    @Override
    public LiveData<List<SubstanceDto>> execute(Void unused) {
        return drugManagementService.getAllSubstances();
    }
}
