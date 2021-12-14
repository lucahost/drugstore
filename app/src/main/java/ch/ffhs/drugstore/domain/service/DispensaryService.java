package ch.ffhs.drugstore.domain.service;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.data.dto.DrugDto;
import ch.ffhs.drugstore.data.repository.DrugRepository;
import ch.ffhs.drugstore.presentation.dispensary.view.FilterState;

public class DispensaryService {
    @Inject
    DrugRepository drugRepository;

    @Inject
    public DispensaryService() {
        // TODO document why this constructor is empty
    }

    public LiveData<List<DrugDto>> getAllDrugs(FilterState<Integer> filterState) {
        boolean favorites = filterState.isFavorites();
        List<Integer> filters = filterState.getFilters();
        String searchTerm = filterState.getSearchFilter();

        if (filters.isEmpty()) {
            return drugRepository.getOnStockDrugs(favorites, searchTerm);
        } else {
            return drugRepository.getOnStockDrugs(favorites, filters, searchTerm);
        }
    }
}
