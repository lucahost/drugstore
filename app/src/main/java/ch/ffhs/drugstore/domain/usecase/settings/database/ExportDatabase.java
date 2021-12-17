package ch.ffhs.drugstore.domain.usecase.settings.database;

import javax.inject.Inject;

import ch.ffhs.drugstore.domain.service.DatabaseService;
import ch.ffhs.drugstore.domain.usecase.UseCase;

public class ExportDatabase implements UseCase<String, Void> {

    private final DatabaseService databaseService;

    @Inject
    public ExportDatabase(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    @Override
    public String execute(Void params) {
        return databaseService.exportDatabase();
    }
}
