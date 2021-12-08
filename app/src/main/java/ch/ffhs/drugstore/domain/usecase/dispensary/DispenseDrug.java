package ch.ffhs.drugstore.domain.usecase.dispensary;

import javax.inject.Inject;

import ch.ffhs.drugstore.domain.service.DispensaryService;
import ch.ffhs.drugstore.domain.usecase.UseCase;

public class DispenseDrug implements UseCase<Void, Void> {
    @Inject
    DispensaryService dispensaryService;

    @Inject
    public DispenseDrug(DispensaryService dispensaryService) {
        this.dispensaryService = dispensaryService;
    }

    @Override
    public Void execute(Void unused) {
        return null;
    }
}