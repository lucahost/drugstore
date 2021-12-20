package ch.ffhs.drugstore.presentation.management.settings.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.chip.Chip;

import java.util.List;
import java.util.function.Consumer;

import ch.ffhs.drugstore.R;
import ch.ffhs.drugstore.databinding.FragmentSettingsBinding;
import ch.ffhs.drugstore.databinding.SettingsEntityChipBinding;
import ch.ffhs.drugstore.presentation.management.settings.viewmodel.SettingsViewModel;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugTypeDto;
import ch.ffhs.drugstore.shared.dto.management.drugs.UnitDto;

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
        viewModel.getDrugTypes().observe(getViewLifecycleOwner(), this::setupDrugTypeChips);
        viewModel.getUnits().observe(getViewLifecycleOwner(), this::setupUnitChips);
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

    /**
     * Setup the filter chips
     *
     * @param drugTypeDtos the drug types to create filter chips
     */
    private void setupDrugTypeChips(@NonNull List<DrugTypeDto> drugTypeDtos) {
        binding.newDrugTypeChip.setOnClickListener(this::onNewDrugTypeClick);
        // Setup dynamic drug type filters
        for (DrugTypeDto drugTypeDto : drugTypeDtos) {
            if (binding.drugTypeChipGroup.findViewById(drugTypeDto.getDrugTypeId()) == null) {
                SettingsEntityChipBinding settingsEntityChipBinding =
                        SettingsEntityChipBinding.inflate(
                                getLayoutInflater());
                settingsEntityChipBinding.entityChip.setId(drugTypeDto.getDrugTypeId());
                settingsEntityChipBinding.entityChip.setText(drugTypeDto.getTitle());
                settingsEntityChipBinding.entityChip.setOnClickListener(
                        this::drugTypeDelete);
                settingsEntityChipBinding.entityChip.setCheckable(false);
                binding.drugTypeChipGroup.addView(settingsEntityChipBinding.entityChip);
            }
        }
    }

    /**
     * Setup the filter chips
     *
     * @param unitDtos the drug types to create filter chips
     */
    private void setupUnitChips(@NonNull List<UnitDto> unitDtos) {
        binding.newUnitChip.setOnClickListener(this::onNewUnitClick);
        // Setup dynamic drug type filters
        for (UnitDto unitDto : unitDtos) {
            if (binding.unitChipGroup.findViewById(unitDto.getUnitId()) == null) {
                SettingsEntityChipBinding settingsEntityChipBinding =
                        SettingsEntityChipBinding.inflate(
                                getLayoutInflater());
                settingsEntityChipBinding.entityChip.setId(unitDto.getUnitId());
                settingsEntityChipBinding.entityChip.setText(unitDto.getTitle());
                settingsEntityChipBinding.entityChip.setOnClickListener(
                        this::unitDelete);
                settingsEntityChipBinding.entityChip.setCheckable(false);
                binding.unitChipGroup.addView(settingsEntityChipBinding.entityChip);
            }
        }
    }


    private void onNewDrugTypeClick(View view) {
        newDialog(getString(R.string.new_drug_type), getString(R.string.create_new_drug_type),
                (String res) -> {
                    if (!res.isEmpty()) {
                        viewModel.createNewDrugType(res, null);
                        Toast.makeText(getContext(), R.string.successfully_created,
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void onNewUnitClick(View view) {
        newDialog(getString(R.string.new_unit), getString(R.string.create_new_unit),
                (String res) -> {
                    if (!res.isEmpty()) {
                        viewModel.createNewUnit(res);
                        Toast.makeText(getContext(), R.string.successfully_created,
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }


    private void drugTypeDelete(View view) {
        int drugTypeId = ((Chip) view).getId();
        String drugType = ((Chip) view).getText().toString();
        try {
            String deleteText = String.format(getString(R.string.confirm_delete_detail), drugType);
            openConfirmDialog(getString(R.string.confirm_delete_drug_type), deleteText,
                    (Void unused) -> {
                        // UnitDto unitDto = viewModel.removeUnitById(unitId);
                        Toast.makeText(getContext(), R.string.todo,
                                Toast.LENGTH_SHORT).show();
                    });
            // DrugTypeDto drugTypeDto = viewModel.removeDrugTypeById(drugTypeId);
        } catch (SQLiteConstraintException constraintException) {
            new AlertDialog.Builder(getContext())
                    .setTitle(R.string.error_delete_drug_type)
                    .setMessage(constraintException.getMessage())
                    .setNegativeButton(R.string.close, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
            return;
        }
    }

    private void unitDelete(View view) {
        int unitId = ((Chip) view).getId();
        String unitTitle = ((Chip) view).getText().toString();
        try {
            String deleteText = String.format(getString(R.string.confirm_delete_detail), unitTitle);
            openConfirmDialog(getString(R.string.confirm_delete_unit), deleteText,
                    (Void unused) -> {
                        // UnitDto unitDto = viewModel.removeUnitById(unitId);
                        Toast.makeText(getContext(), R.string.todo,
                                Toast.LENGTH_SHORT).show();
                    });
        } catch (SQLiteConstraintException constraintException) {
            new AlertDialog.Builder(getContext())
                    .setTitle(R.string.error_delete_unit)
                    .setMessage(constraintException.getMessage())
                    .setNegativeButton(R.string.close, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
            return;
        }
    }

    private void openConfirmDialog(String title, String detail, Consumer<Void> callback) {
        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        alert.setMessage(detail);
        alert.setTitle(title);

        alert.setPositiveButton(R.string.confirm,
                (dialog, whichButton) -> callback.accept(null));

        alert.setNegativeButton(R.string.cancel, null);

        alert.show();
    }

    private void newDialog(String title, String message, Consumer<String> callback) {
        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        final EditText edittext = new EditText(getContext());
        alert.setMessage(message);
        alert.setTitle(title);

        alert.setView(edittext);

        alert.setPositiveButton(R.string.create,
                (dialog, whichButton) -> callback.accept(edittext.getText().toString()));

        alert.setNegativeButton(R.string.cancel, null);

        alert.show();
    }
}