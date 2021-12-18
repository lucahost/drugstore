package ch.ffhs.drugstore.data.database;

import android.app.Application;

import androidx.sqlite.db.SupportSQLiteOpenHelper;

import javax.inject.Inject;
/**
 * class for export and import Database
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class DatabaseInterface {

    private final DrugstoreDatabase db;
    private final Application app;

    @Inject
    public DatabaseInterface(Application application) {
        this.app = application;
        this.db = DrugstoreDatabase.getDatabase(application);
    }

    public String exportDatabase() {
        SupportSQLiteOpenHelper dbOpenHelper = db.getOpenHelper();
        String dbName = dbOpenHelper.getDatabaseName();

        String dbFilePath = app.getApplicationContext().getDatabasePath(dbName).getAbsolutePath();

        return dbFilePath;
    }

    public void importDatabase() {
    }
}
