package ch.ffhs.drugstore.presentation.management.drugs.view.dialog.create;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.inject.Inject;

import ch.ffhs.drugstore.R;
import ch.ffhs.drugstore.databinding.DialogCreateDrugBinding;
import ch.ffhs.drugstore.presentation.helpers.InputValidation;
import ch.ffhs.drugstore.presentation.management.drugs.viewmodel.DrugsViewModel;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugTypeDto;
import ch.ffhs.drugstore.shared.dto.management.drugs.SubstanceDto;
import ch.ffhs.drugstore.shared.dto.management.drugs.UnitDto;
import dagger.hilt.android.AndroidEntryPoint;

/**
 * Dialog to create a new drug.
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
@AndroidEntryPoint
public class CreateDrugDialogFragment extends DialogFragment {

    public static final String TAG = "CreateDrug";

    private DialogCreateDrugBinding binding = null;
    private DrugsViewModel viewModel = null;
    private ConfirmCreateDrugListener confirmCreateDrugListener = null;

    @Inject
    public CreateDrugDialogFragment() {
        // Empty constructor is required by the Android framework.
    }

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(requireActivity()).get(DrugsViewModel.class);
        binding = DialogCreateDrugBinding.inflate(getLayoutInflater());
        setDrugTypeRadioButtons();
        setDrugUnitRadioButtons();
        setSubstanceAutoComplete();
        return getAlertDialog();
    }

    /**
     * @return the alert dialog
     */
    @NonNull
    private AlertDialog getAlertDialog() {
        AlertDialog dialog =
                new AlertDialog.Builder(requireContext())
                        .setView(binding.getRoot())
                        .setTitle(getString(R.string.create_drug))
                        .setPositiveButton(getString(R.string.create), null)
                        .setNegativeButton(getString(R.string.cancel), null)
                        .create();
        dialog.setOnShowListener(
                d -> {
                    Button button = ((AlertDialog) d).getButton(DialogInterface.BUTTON_POSITIVE);
                    button.setOnClickListener(view -> validateInputAndCreateDrug());
                });
        return dialog;
    }

    /**
     *
     */
    private void validateInputAndCreateDrug() {
        boolean nameNotEmpty =
                InputValidation.validateTextNotEmpty(
                        binding.nameText, binding.nameTextLayout,
                        getString(R.string.error_name_required));
        boolean substanceNotEmpty =
                InputValidation.validateTextNotEmpty(
                        binding.substanceText,
                        binding.substanceTextLayout,
                        getString(R.string.error_substance_required));
        boolean dosageNotEmpty =
                InputValidation.validateTextNotEmpty(
                        binding.dosageText,
                        binding.dosageTextLayout,
                        getString(R.string.error_dosage_required));

        if (nameNotEmpty && substanceNotEmpty && dosageNotEmpty) {
            String name = Objects.requireNonNull(binding.nameText.getText()).toString();
            String substance = Objects.requireNonNull(binding.substanceText.getText()).toString();
            String dosage = Objects.requireNonNull(binding.dosageText.getText()).toString();
            int drugTypeId = binding.drugTypeRadioGroup.getCheckedRadioButtonId();
            int unitId = binding.dispenseUnitRadioGroup.getCheckedRadioButtonId();
            String tolerance = Objects.requireNonNull(binding.toleranceText.getText()).toString();
            boolean isFavorite = binding.isFavoriteCheckbox.isChecked();

            confirmCreateDrugListener.onConfirmCreateDrug(
                    name, substance, dosage, drugTypeId, unitId, tolerance, isFavorite);
        }
    }

    /**
     *
     */
    private void setDrugTypeRadioButtons() {
        viewModel
                .getDrugTypes()
                .observe(
                        this,
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

    /**
     *
     */
    private void setDrugUnitRadioButtons() {
        viewModel
                .getDrugUnits()
                .observe(
                        this,
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

    /**
     *
     */
    private void setSubstanceAutoComplete() {
        viewModel
                .getSubstances()
                .observe(
                        this,
                        substanceDtoList -> {
                            List<String> substanceTitles =
                                    substanceDtoList.stream()
                                            .map(SubstanceDto::getTitle)
                                            .collect(Collectors.toList());
                            binding.substanceText.setAdapter(
                                    new ArrayAdapter<>(
                                            getContext(),
                                            android.R.layout.simple_dropdown_item_1line,
                                            substanceTitles));
                        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onDetach() {
        super.onDetach();
        this.confirmCreateDrugListener = null;
    }
}
