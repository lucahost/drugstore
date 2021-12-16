package ch.ffhs.drugstore.domain.service;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.data.repository.DrugRepository;
import ch.ffhs.drugstore.presentation.dispensary.view.FilterState;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugDto;

/**
 * This class represents a service to dispense drugs
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class DispensaryService {
    private final DrugRepository drugRepository;

    /**
     * Provide the drugRepository
     *
     * @param drugRepository
     */
    @Inject
    public DispensaryService(DrugRepository drugRepository) {
        this.drugRepository = drugRepository;
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

    public void toggleDrugIsFavorite(int drugId) {
        drugRepository.toggleDrugIsFavorite(drugId);
    }
}
