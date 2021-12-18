package ch.ffhs.drugstore.domain.usecase.dispensary;

import javax.inject.Inject;

import ch.ffhs.drugstore.domain.service.DispensaryService;
import ch.ffhs.drugstore.domain.usecase.UseCase;
import ch.ffhs.drugstore.shared.exceptions.DrugstoreException;

/**
 * Use-Case class to toggle favorite drug
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class ToggleDrugIsFavorite implements UseCase<Void, Integer> {
    private final DispensaryService dispensaryService;

    /**
     * Construct a {@link ToggleDrugIsFavorite} use case
     *
     * @param dispensaryService dispensary service
     */
    @Inject
    public ToggleDrugIsFavorite(DispensaryService dispensaryService) {
        this.dispensaryService = dispensaryService;
    }

    /**
     * {@inheritDoc}
     *
     * @param drugId the id of the drug to toggle favorite
     * @return Void
     * @throws DrugstoreException
     */
    @Override
    public Void execute(Integer drugId) throws DrugstoreException {
        dispensaryService.toggleDrugIsFavorite(drugId);
        return null;
    }
}