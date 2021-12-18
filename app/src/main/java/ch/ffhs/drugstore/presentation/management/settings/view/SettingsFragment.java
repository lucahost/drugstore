package ch.ffhs.drugstore.presentation.management.settings.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        // TODO: handle this
        getContext().grantUriPermission("ch.ffhs.drugstore.fileprovider", dbFileUri,
                Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("application/x-sqlite3");
        shareIntent.putExtra(Intent.EXTRA_STREAM, dbFileUri);
        startActivity(Intent.createChooser(shareIntent, "DB Export"));
    }

}