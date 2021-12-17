package ch.ffhs.drugstore.presentation.management.settings.viewmodel;

import static ch.ffhs.drugstore.shared.extensions.FileExtensions.zipDirectory;

import android.app.Application;
import android.net.Uri;

import androidx.core.content.FileProvider;
import androidx.lifecycle.AndroidViewModel;

import java.io.File;

import javax.inject.Inject;

import ch.ffhs.drugstore.domain.usecase.settings.database.ExportDatabase;
import ch.ffhs.drugstore.domain.usecase.settings.database.ImportDatabase;
import ch.ffhs.drugstore.presentation.SingleLiveEvent;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class SettingsViewModel extends AndroidViewModel {

    private final SingleLiveEvent<Uri> databaseExportedEvent = new SingleLiveEvent<>();
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
        File targetFile = new File(
                app.getApplicationContext().getCacheDir() + "/export/db_export.zip");

        zipDirectory(dbDirectory, targetFile);

        Uri fileUri = FileProvider.getUriForFile(app.getApplicationContext(),
                "ch.ffhs.drugstore.fileprovider", targetFile);
        databaseExportedEvent.setValue(fileUri);

    }

    public void importDatabase() {
        importDatabase.execute(null);
        databaseExportedEvent.call();
    }

    public SingleLiveEvent<Uri> getDatabaseExportedEvent() {
        return databaseExportedEvent;
    }

}
