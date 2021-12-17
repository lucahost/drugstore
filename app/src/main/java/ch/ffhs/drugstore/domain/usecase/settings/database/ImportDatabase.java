package ch.ffhs.drugstore.domain.usecase.settings.database;

import javax.inject.Inject;

import ch.ffhs.drugstore.domain.service.DatabaseService;
import ch.ffhs.drugstore.domain.usecase.UseCase;

public class ImportDatabase implements UseCase<Void, Void> {

    private final DatabaseService databaseService;

    @Inject
    public ImportDatabase(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    @Override
    public Void execute(Void params) {
        databaseService.importDatabase();
        return null;
    }
}