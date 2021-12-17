package ch.ffhs.drugstore.domain.usecase.dispensary;

import javax.inject.Inject;

import ch.ffhs.drugstore.domain.service.DispensaryService;
import ch.ffhs.drugstore.domain.usecase.UseCase;
import ch.ffhs.drugstore.shared.exceptions.DrugstoreException;

public class ToggleDrugIsFavorite implements UseCase<Void, Integer> {
    @Inject
    DispensaryService dispensaryService;

    @Inject
    public ToggleDrugIsFavorite(DispensaryService dispensaryService) {
        this.dispensaryService = dispensaryService;
    }

    @Override
    public Void execute(Integer drugId) throws DrugstoreException {
        dispensaryService.toggleDrugIsFavorite(drugId);
        return null;
    }
}