package ch.ffhs.drugstore.domain.usecase.dispensary;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.domain.service.DispensaryService;
import ch.ffhs.drugstore.domain.usecase.UseCase;
import ch.ffhs.drugstore.presentation.dispensary.view.FilterState;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugDto;

/**
 * Use-Case class to get filtered dispensary items
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class GetAllDispensaryItems
        implements UseCase<LiveData<List<DrugDto>>, FilterState<Integer>> {
    private final DispensaryService dispensaryService;

    /**
     * Construct a {@link GetAllDispensaryItems} use case
     *
     * @param dispensaryService dispensary service
     */
    @Inject
    public GetAllDispensaryItems(DispensaryService dispensaryService) {
        this.dispensaryService = dispensaryService;
    }

    /**
     * {@inheritDoc}
     *
     * @param filterState create drug input dto of the use case
     * @return filtered live data list of drugs
     */
    @Override
    public LiveData<List<DrugDto>> execute(FilterState<Integer> filterState) {
        return dispensaryService.getAllDrugs(filterState);
    }
}
