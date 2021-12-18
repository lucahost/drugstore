package ch.ffhs.drugstore.domain.usecase.settings.database;

import javax.inject.Inject;

import ch.ffhs.drugstore.domain.service.DatabaseService;
import ch.ffhs.drugstore.domain.usecase.UseCase;

/**
 * Use-Case class to export the database
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class ExportDatabase implements UseCase<String, Void> {

    private final DatabaseService databaseService;

    /**
     * Construct a {@link ExportDatabase} use case
     *
     * @param databaseService database service
     */
    @Inject
    public ExportDatabase(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    /**
     * {@inheritDoc}
     *
     * @param unused no parameters
     * @return file path string
     */
    @Override
    public String execute(Void unused) {
        return databaseService.exportDatabase();
    }
}
