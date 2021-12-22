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
import ch.ffhs.drugstore.presentation.helpers.SingleLiveEvent;
import dagger.hilt.android.lifecycle.HiltViewModel;

/**
 * View model for settings.
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
@HiltViewModel
public class SettingsViewModel extends AndroidViewModel {
    private final SingleLiveEvent<Uri> databaseExportedEvent = new SingleLiveEvent<>();
    private final Application app;

    @Inject
    ExportDatabase exportDatabase;

    @Inject
    ImportDatabase importDatabase;

    /**
     * Constructs a {@link SettingsViewModel}
     *
     * @param app global application state
     */
    @Inject
    public SettingsViewModel(Application app) {
        super(app);
        this.app = app;
    }

    /**
     * Gathers the current apps database files, saves them to a zip drive Creates a URI and invokes
     * the databaseExportedEvent
     */
    public void exportDatabase() {
        String dbName = exportDatabase.execute(null);
        File dbDirectory = new File(dbName).getParentFile();
        File targetFile = new File(
                app.getApplicationContext().getCacheDir() + "/export/db_export.zip");

        zipDirectory(app.getApplicationContext(), dbDirectory, targetFile);

        Uri fileUri =
                FileProvider.getUriForFile(
                        app.getApplicationContext(), "ch.ffhs.drugstore.fileprovider", targetFile);
        databaseExportedEvent.setValue(fileUri);
    }

    /**
     * Should open a file-chooser where the user can provide the sqlite db Imports this db using the
     * importDB UseCase
     */
    public void importDatabase() {
        importDatabase.execute(null);
        databaseExportedEvent.call();
    }

    /**
     * Used to send an event to the view from the viewModel
     *
     * @return {@link SingleLiveEvent} databaseExportedEvent
     */
    public SingleLiveEvent<Uri> getDatabaseExportedEvent() {
        return databaseExportedEvent;
    }
}
