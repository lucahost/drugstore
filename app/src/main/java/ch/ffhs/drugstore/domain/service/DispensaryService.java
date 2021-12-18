package ch.ffhs.drugstore.domain.service;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.R;
import ch.ffhs.drugstore.data.repository.DrugRepository;
import ch.ffhs.drugstore.presentation.dispensary.view.FilterState;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugDto;
import ch.ffhs.drugstore.shared.exceptions.DrugNotFoundException;
import ch.ffhs.drugstore.shared.exceptions.DrugstoreException;

/**
 * This service class dispenses drugs
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class DispensaryService {
    private final DrugRepository drugRepository;

    @Inject
    public DispensaryService(DrugRepository drugRepository) {
        this.drugRepository = drugRepository;
    }

    /**
     * gets all drugs and filters for favorite
     * @param filterState
     * @return Live Data List with favorite and searched drugs
     */
    public LiveData<List<DrugDto>> getAllDrugs(@NonNfitgggdfgjgjull FilterState<Integer> filterState) {
        boolean favorites = filterState.isFavorites();
        List<Integer> filters = filterState.getFilters();
        String searchTerm = filterState.getSearchFilter();

        if (filters.isEmpty()) {
            return drugRepository.getOnStockDrugs(favorites, searchTerm);
        } else {
            return drugRepository.getOnStockDrugs(favorites, filters, searchTerm);
        }
    }

    /**
     * checks if drug from search exists otherwise throws exception
     * @param drugId
     * @throws DrugstoreException
     */
    public void toggleDrugIsFavorite(int drugId) throws DrugstoreException {
        DrugDto drug = drugRepository.getDrugById(drugId);
        if (drug == null) {
            throw new DrugNotFoundException(R.string.drug_not_found);
        }
        drugRepository.toggleDrugIsFavorite(drugId);
    }
}
