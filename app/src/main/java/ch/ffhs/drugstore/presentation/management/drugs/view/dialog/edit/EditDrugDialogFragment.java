package ch.ffhs.drugstore.presentation.management.drugs.view.dialog.edit;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import ch.ffhs.drugstore.R;
import ch.ffhs.drugstore.databinding.DialogEditDrugBinding;
import ch.ffhs.drugstore.presentation.helpers.InputValidation;
import ch.ffhs.drugstore.presentation.management.drugs.viewmodel.DrugsViewModel;
import ch.ffhs.drugstore.shared.dto.management.drugs.SubstanceDto;
import dagger.assisted.Assisted;
import dagger.assisted.AssistedInject;
import dagger.hilt.android.AndroidEntryPoint;

/**
 * Dialog for the edit of a drug.
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
@AndroidEntryPoint
public class EditDrugDialogFragment extends DialogFragment {

    public static final String TAG = "EditDrug";
    public static final String ARG_DRUG_ID = "drugId";
    public static final String ARG_DRUG_TITLE = "drugTitle";
    public static final String ARG_DRUG_SUBSTANCE = "substance";
    public static final String ARG_DRUG_DOSAGE = "dosage";
    public static final String ARG_DRUG_TYPE = "drugType";
    public static final String ARG_DRUG_UNIT = "drugUnit";
    public static final String ARG_DRUG_TOLERANCE = "tolerance";
    public static final String ARG_DRUG_FAVORITE = "isFavorite";

    private DialogEditDrugBinding binding = null;
    private DrugsViewModel viewModel = null;
    private Integer drugId = null;
    private String drugTitle = null;
    private String substance = null;
    private String dosage = null;
    private String drugType = null;
    private String drugUnit = null;
    private Double tolerance = null;
    private Boolean isFavorite = null;
    private ConfirmEditDrugListener confirmEditDrugListener = null;

    /**
     * Constructs a {@link EditDrugDialogFragment} by {@link EditDrugDialogFragmentFactory}.
     *
     * @param args fragment arguments
     */
    @AssistedInject
    public EditDrugDialogFragment(
            @Assisted("editDrugDialogFragmentArgs") EditDrugDialogFragmentArgs args) {
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_DRUG_ID, args.getDrugId());
        bundle.putString(ARG_DRUG_TITLE, args.getDrugTitle());
        bundle.putString(ARG_DRUG_SUBSTANCE, args.getSubstance());
        bundle.putString(ARG_DRUG_DOSAGE, args.getDosage());
        bundle.putString(ARG_DRUG_TYPE, args.getDrugType());
        bundle.putString(ARG_DRUG_UNIT, args.getDrugUnit());
        bundle.putDouble(ARG_DRUG_TOLERANCE, args.getTolerance());
        bundle.putBoolean(ARG_DRUG_FAVORITE, args.isFavorite());
        setArguments(bundle);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (getParentFragment() instanceof ConfirmEditDrugListener) {
            this.confirmEditDrugListener = (ConfirmEditDrugListener) getParentFragment();
        }
        if (context instanceof ConfirmEditDrugListener) {
            this.confirmEditDrugListener = (ConfirmEditDrugListener) context;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            drugId = getArguments().getInt(ARG_DRUG_ID);
            drugTitle = getArguments().getString(ARG_DRUG_TITLE);
            substance = getArguments().getString(ARG_DRUG_SUBSTANCE);
            dosage = getArguments().getString(ARG_DRUG_DOSAGE);
            drugType = getArguments().getString(ARG_DRUG_TYPE);
            drugUnit = getArguments().getString(ARG_DRUG_UNIT);
            tolerance = getArguments().getDouble(ARG_DRUG_TOLERANCE);
            isFavorite = getArguments().getBoolean(ARG_DRUG_FAVORITE);
        }
    }

    /**
     * {@inheritDoc}
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(requireActivity()).get(DrugsViewModel.class);
        binding = DialogEditDrugBinding.inflate(getLayoutInflater());
        setDrugTypeRadioButtons();
        setDrugUnitRadioButtons();
        setSubstanceAutoComplete();
        binding.nameText.setText(drugTitle);
        binding.substanceText.setText(substance);
        binding.dosageText.setText(dosage);
        binding.toleranceText.setText(getString(R.string.tolerance_double, tolerance));
        binding.isFavoriteCheckbox.setChecked(isFavorite);
        return getAlertDialog();
    }

    /**
     * @return the alert dialog
     */
    @NonNull
    private AlertDialog getAlertDialog() {
        AlertDialog dialog = new AlertDialog.Builder(requireContext())
                .setView(binding.getRoot())
                .setTitle(getString(R.string.edit_drug))
                .setPositiveButton(getString(R.string.save), null)
                .setNegativeButton(getString(R.string.cancel), null)
                .create();
        dialog.setOnShowListener(d -> {
            Button button = ((AlertDialog) d).getButton(AlertDialog.BUTTON_POSITIVE);
            button.setOnClickListener(view -> validateInputAndEditDrug());
        });
        return dialog;
    }

    /**
     * Validates inputs and confirms the edit if there are no validation errors.
     */
    private void validateInputAndEditDrug() {
        boolean nameNotEmpty = InputValidation.validateTextNotEmpty(
                binding.nameText,
                binding.nameTextLayout,
                getString(R.string.error_name_required));
        boolean substanceNotEmpty = InputValidation.validateTextNotEmpty(
                binding.substanceText,
                binding.substanceTextLayout,
                getString(R.string.error_substance_required));
        boolean dosageNotEmpty = InputValidation.validateTextNotEmpty(
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

            confirmEditDrugListener.onConfirmEditDrug(drugId, name, substance, dosage, drugTypeId,
                    unitId,
                    tolerance, isFavorite);
        }
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        setArguments(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onDetach() {
        super.onDetach();
        this.confirmEditDrugListener = null;
        setArguments(null);
    }

    /**
     *
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setDrugTypeRadioButtons() {
        viewModel.getDrugTypes().observe(this,
                list -> list.forEach(item -> {
                    RadioButton radio = new RadioButton(getContext());
                    radio.setId(item.getDrugTypeId());
                    radio.setText(item.getTitle());
                    radio.setChecked(drugType.equals(item.getTitle()));
                    binding.drugTypeRadioGroup.addView(radio);
                }));
    }

    /**
     *
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setDrugUnitRadioButtons() {
        viewModel.getDrugUnits().observe(this,
                list -> list.forEach(item -> {
                    RadioButton radio = new RadioButton(getContext());
                    radio.setId(item.getUnitId());
                    radio.setText(item.getTitle());
                    radio.setChecked(drugUnit.equals(item.getTitle()));
                    binding.dispenseUnitRadioGroup.addView(radio);
                }));
    }

    /**
     *
     */
    private void setSubstanceAutoComplete() {
        viewModel.getSubstances().observe(this, substanceDtoList -> {
            List<String> substanceTitles = substanceDtoList.stream().map(
                    SubstanceDto::getTitle).collect(
                    Collectors.toList());
            binding.substanceText.setAdapter(
                    new ArrayAdapter<>(getContext(),
                            android.R.layout.simple_dropdown_item_1line, substanceTitles
                    ));
        });
    }
}
