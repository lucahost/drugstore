package ch.ffhs.drugstore.domain.usecase.management.drugs;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.domain.service.DrugManagementService;
import ch.ffhs.drugstore.domain.usecase.UseCase;
import ch.ffhs.drugstore.shared.dto.management.drugs.UnitDto;

/**
 * Use-Case class to get all drug units
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class GetDrugUnits implements UseCase<LiveData<List<UnitDto>>, Void> {
    private final DrugManagementService drugManagementService;

    /**
     * Construct a {@link GetDrugUnits} use case
     *
     * @param drugManagementService drug management service
     */
    @Inject
    public GetDrugUnits(DrugManagementService drugManagementService) {
        this.drugManagementService = drugManagementService;
    }

    /**
     * {@inheritDoc}
     *
     * @param unused no parameters
     * @return live data list of drug units
     */
    @Override
    public LiveData<List<UnitDto>> execute(Void unused) {
        return drugManagementService.getAllUnits();
    }
}
