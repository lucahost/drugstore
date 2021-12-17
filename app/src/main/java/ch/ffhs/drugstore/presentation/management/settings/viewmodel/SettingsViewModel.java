package ch.ffhs.drugstore.presentation.management.settings.viewmodel;

import static ch.ffhs.drugstore.shared.extensions.FileExtensions.copyDirectory;
import static ch.ffhs.drugstore.shared.extensions.FileExtensions.zipDirectory;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import java.io.File;
import java.io.IOException;

import javax.inject.Inject;

import ch.ffhs.drugstore.domain.usecase.settings.database.ExportDatabase;
import ch.ffhs.drugstore.domain.usecase.settings.database.ImportDatabase;
import ch.ffhs.drugstore.presentation.SingleLiveEvent;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class SettingsViewModel extends AndroidViewModel {

    private final SingleLiveEvent<String> databaseExportedEvent = new SingleLiveEvent<>();
    private final Application app;

    @Inject
    ExportDatabase exportDatabase;

    @Inject
    ImportDatabase importDatabase;

    @Inject
    public SettingsViewModel(Application app) {
        super(app);
        this.app = app;
    }

    public void exportDatabase() {
        String dbName = exportDatabase.execute(null);
        File dbDirectory = new File(dbName).getParentFile();
        File destDirectory = new File(app.getApplicationContext().getFilesDir(), "/export/");
        try {
            copyDirectory(dbDirectory, destDirectory);
            String zipName = zipDirectory(destDirectory, "db_export.zip");
            databaseExportedEvent.setValue(zipName);
        } catch (IOException ex) {
            String errorMessage = ex.getMessage();
            databaseExportedEvent.setValue(errorMessage);
            return;
        }
    }

    public void importDatabase() {
        importDatabase.execute(null);
        databaseExportedEvent.call();
    }

    public SingleLiveEvent<String> getDatabaseExportedEvent() {
        return databaseExportedEvent;
    }

}
