package ch.ffhs.drugstore.presentation.management.drugs.view.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.Objects;

import javax.inject.Inject;

import ch.ffhs.drugstore.R;
import ch.ffhs.drugstore.databinding.DialogCreateDrugBinding;
import ch.ffhs.drugstore.presentation.InputValidation;
import ch.ffhs.drugstore.presentation.management.drugs.viewmodel.DrugsViewModel;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugTypeDto;
import ch.ffhs.drugstore.shared.dto.management.drugs.UnitDto;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CreateDrugDialogFragment extends DialogFragment {

    public static final String TAG = "CreateDrug";
    DialogCreateDrugBinding binding;
    DrugsViewModel viewModel;
    private ConfirmCreateDrugListener confirmCreateDrugListener;

    @Inject
    public CreateDrugDialogFragment() {
        /* TODO document why this constructor is empty */
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (getParentFragment() instanceof ConfirmCreateDrugListener) {
            this.confirmCreateDrugListener = (ConfirmCreateDrugListener) getParentFragment();
        }
        if (context instanceof ConfirmCreateDrugListener) {
            this.confirmCreateDrugListener = (ConfirmCreateDrugListener) context;
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(requireActivity()).get(DrugsViewModel.class);
        binding = DialogCreateDrugBinding.inflate(getLayoutInflater());
        setDrugTypeRadioButtons();
        setDrugUnitRadioButtons();
        return getAlertDialog();
    }

    @NonNull
    private AlertDialog getAlertDialog() {
        AlertDialog dialog = new AlertDialog.Builder(requireContext())
                .setView(binding.getRoot())
                .setTitle(getString(R.string.create_drug))
                .setPositiveButton(getString(R.string.create), null)
                .setNegativeButton(getString(R.string.cancel), null)
                .create();
        dialog.setOnShowListener(d -> {
            Button button = ((AlertDialog) d).getButton(AlertDialog.BUTTON_POSITIVE);
            button.setOnClickListener(view -> validateInputAndCreateDrug());
        });
        return dialog;
    }

    private void validateInputAndCreateDrug() {
        boolean nameNotEmpty = InputValidation.validateTextNotEmpty(
                binding.nameText,
                binding.nameTextLayout,
                getString(R.string.error_name_required));
        boolean dosageNotEmpty = InputValidation.validateTextNotEmpty(
                binding.dosageText,
                binding.dosageTextLayout,
                getString(R.string.error_dosage_required));

        if (nameNotEmpty && dosageNotEmpty) {
            String name = Objects.requireNonNull(binding.nameText.getText()).toString();
            String dosage = Objects.requireNonNull(binding.dosageText.getText()).toString();
            int drugTypeId = binding.drugTypeRadioGroup.getCheckedRadioButtonId();
            int unitId = binding.dispenseUnitRadioGroup.getCheckedRadioButtonId();
            String tolerance = Objects.requireNonNull(binding.toleranceText.getText()).toString();
            boolean isFavorite = binding.isFavoriteCheckbox.isChecked();

            confirmCreateDrugListener.onConfirmCreateDrug(name, dosage, drugTypeId, unitId,
                    tolerance, isFavorite);
        }
    }

    private void setDrugTypeRadioButtons() {
        viewModel.getDrugTypes().observe(this,
                list -> {
                    int i = 0;
                    for (DrugTypeDto item : list) {
                        RadioButton radio = new RadioButton(getContext());
                        radio.setId(item.getDrugTypeId());
                        radio.setText(item.getTitle());
                        radio.setChecked(i == 0);
                        binding.drugTypeRadioGroup.addView(radio);
                        i++;
                    }
                });
    }

    private void setDrugUnitRadioButtons() {
        viewModel.getDrugUnits().observe(this,
                list -> {
                    int i = 0;
                    for (UnitDto item : list) {
                        RadioButton radio = new RadioButton(getContext());
                        radio.setId(item.getUnitId());
                        radio.setText(item.getTitle());
                        radio.setChecked(i == 0);
                        binding.dispenseUnitRadioGroup.addView(radio);
                        i++;
                    }
                });
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.confirmCreateDrugListener = null;
    }

    public interface ConfirmCreateDrugListener {
        void onConfirmCreateDrug(
                String name, String dosage, int drugTypeId, int unitId, String tolerance,
                boolean isFavorite);
    }
}
