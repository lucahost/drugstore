package ch.ffhs.drugstore.domain.usecase.management.drugs;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.domain.service.DrugManagementService;
import ch.ffhs.drugstore.domain.usecase.UseCase;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugDto;

/**
 * Use-Case class to get an existing drug
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class GetDrugs implements UseCase<LiveData<List<DrugDto>>, Void> {
    private final DrugManagementService drugManagementService;

    /**
     * Construct a {@link GetDrugs} use case
     *
     * @param drugManagementService drug management service
     */
    @Inject
    public GetDrugs(DrugManagementService drugManagementService) {
        this.drugManagementService = drugManagementService;
    }

    /**
     * {@inheritDoc}
     *
     * @param unused no parameters
     * @return live data list of drugs
     */
    @Override
    public LiveData<List<DrugDto>> execute(Void unused) {
        return drugManagementService.getAllDrugs();
    }
}
