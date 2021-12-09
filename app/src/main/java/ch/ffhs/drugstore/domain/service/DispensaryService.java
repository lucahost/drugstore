package ch.ffhs.drugstore.domain.service;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.data.dto.DrugDto;
import ch.ffhs.drugstore.data.repository.DrugRepository;
import ch.ffhs.drugstore.presentation.dispensary.view.DispensaryFilters;
import ch.ffhs.drugstore.presentation.dispensary.view.FilterState;

public class DispensaryService {
    @Inject
    DrugRepository drugRepository;

    @Inject
    public DispensaryService() {
        // TODO document why this constructor is empty
    }

    public LiveData<List<DrugDto>> getAllDrugs(FilterState<DispensaryFilters> filterState) {
        boolean favorites = filterState.isFavorites();
        boolean drugTypeFilter = !filterState.getFiltersAsStrings().isEmpty();
        boolean searchTermFilter = !filterState.getSearchFilter().isEmpty();

        if (drugTypeFilter) {
            if (searchTermFilter) {
                return drugRepository.getOnStockDrugs(favorites, filterState.getFiltersAsStrings(), filterState.getSearchFilter());
            } else {
                return drugRepository.getOnStockDrugs(favorites, filterState.getFiltersAsStrings());
            }
        } else {
            if (searchTermFilter) {
                return drugRepository.getOnStockDrugs(favorites, filterState.getSearchFilter());
            } else {
                return drugRepository.getOnStockDrugs(favorites);
            }
        }
    }
}
