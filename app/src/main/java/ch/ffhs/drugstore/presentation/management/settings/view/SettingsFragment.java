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

public class SettingsFragment extends Fragment {

    FragmentSettingsBinding binding;
    private SettingsViewModel viewModel;

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSettingsBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(SettingsViewModel.class);
        setupRecyclerView();
    }


    private void setupRecyclerView() {
        viewModel.getDatabaseExportedEvent().observe(this, this::exportDb);
        binding.exportButton.setOnClickListener(v -> viewModel.exportDatabase());
    }

    private void exportDb(Uri dbFileUri) {
        getContext().grantUriPermission("ch.ffhs.drugstore.fileprovider", dbFileUri,
                Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("application/x-sqlite3");
        shareIntent.putExtra(Intent.EXTRA_STREAM, dbFileUri);
        startActivity(Intent.createChooser(shareIntent, "DB Export"));
    }

}