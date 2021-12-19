package ch.ffhs.drugstore.presentation.management.settings.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import ch.ffhs.drugstore.databinding.FragmentSettingsBinding;
import ch.ffhs.drugstore.presentation.management.settings.viewmodel.SettingsViewModel;

/**
 * Settings Fragment
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class SettingsFragment extends Fragment {

    private FragmentSettingsBinding binding;
    private SettingsViewModel viewModel;
    private Uri dbFileUri;
    private ActivityResultLauncher<Intent> activityResultLauncher;

    /**
     * {@inheritDoc}
     */
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSettingsBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> context.revokeUriPermission(dbFileUri,
                        Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                                | Intent.FLAG_GRANT_READ_URI_PERMISSION));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(SettingsViewModel.class);
        setupRecyclerView();
    }

    /**
     * Setup the recycler view list
     */
    private void setupRecyclerView() {
        viewModel.getDatabaseExportedEvent().observe(this, this::exportDb);
        binding.exportButton.setOnClickListener(v -> viewModel.exportDatabase());
    }

    /**
     * @param dbFileUri the file uri of the database
     */
    private void exportDb(Uri dbFileUri) {
        this.dbFileUri = dbFileUri;
        try {
            Context context = getContext();
            if (context == null) {
                System.err.println("no context in exportDb");
                return;
            }
            getContext().grantUriPermission("ch.ffhs.drugstore.fileprovider",
                    dbFileUri,
                    Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } catch (Exception ex) {
            System.err.println("grantUriPermissionFailed");
            return;
        }
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setDataAndType(dbFileUri, "application/zip");
        shareIntent.putExtra(Intent.EXTRA_STREAM, dbFileUri);
        shareIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        Intent createChooserIntent = Intent.createChooser(shareIntent, "DB Export");
        activityResultLauncher.launch(createChooserIntent);
    }
}