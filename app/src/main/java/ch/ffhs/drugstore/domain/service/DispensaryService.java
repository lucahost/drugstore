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
        List<String> filters = filterState.getFiltersAsStrings();
        String searchTerm = filterState.getSearchFilter();

        if (filters.isEmpty()) {
            return drugRepository.getOnStockDrugs(favorites, searchTerm);
        } else {
            return drugRepository.getOnStockDrugs(favorites, filterState.getFiltersAsStrings(), searchTerm);
        }
    }
}
