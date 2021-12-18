package ch.ffhs.drugstore.domain.service;

import javax.inject.Inject;

import ch.ffhs.drugstore.data.database.DatabaseInterface;

/**
 * this service class extends the Database Interface
 * and exports and imports database
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class DatabaseService {
    private final DatabaseInterface databaseInterface;

    @Inject
    public DatabaseService(DatabaseInterface databaseInterface) {
        this.databaseInterface = databaseInterface;
    }

    public String exportDatabase() {
        return databaseInterface.exportDatabase();
    }

    public void importDatabase() {
        databaseInterface.importDatabase();
    }
}
