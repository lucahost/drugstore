package ch.ffhs.drugstore.domain.service;

import javax.inject.Inject;

import ch.ffhs.drugstore.data.database.DatabaseInterface;

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
