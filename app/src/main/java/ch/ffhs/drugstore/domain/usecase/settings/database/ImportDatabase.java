package ch.ffhs.drugstore.domain.usecase.settings.database;

import javax.inject.Inject;

import ch.ffhs.drugstore.domain.service.DatabaseService;
import ch.ffhs.drugstore.domain.usecase.UseCase;

/**
 * Use-Case class to import a database
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class ImportDatabase implements UseCase<Void, Void> {

    private final DatabaseService databaseService;

    /**
     * Construct a {@link ImportDatabase} use case
     *
     * @param databaseService database service
     */
    @Inject
    public ImportDatabase(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    /**
     * {@inheritDoc}
     *
     * @param unused no parameters
     * @return Void
     */
    @Override
    public Void execute(Void unused) {
        databaseService.importDatabase();
        return null;
    }
}
