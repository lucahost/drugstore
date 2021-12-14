package ch.ffhs.drugstore.presentation.management.drugs.view.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.Objects;

import ch.ffhs.drugstore.R;
import ch.ffhs.drugstore.databinding.DialogEditDrugBinding;
import ch.ffhs.drugstore.presentation.management.drugs.viewmodel.DrugsViewModel;
import dagger.assisted.Assisted;
import dagger.assisted.AssistedInject;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class EditDrugDialogFragment extends DialogFragment {

    public static final String TAG = "EditDrug";
    public static final String ARG_DRUG_ID = "drugId";
    public static final String ARG_DRUG_TITLE = "drugTitle";
    public static final String ARG_DRUG_DOSAGE = "dosage";
    public static final String ARG_DRUG_TYPE = "drugType";
    public static final String ARG_DRUG_TOLERANCE = "tolerance";
    public static final String ARG_DRUG_FAVORITE = "isFavorite";
    private int drugId;
    private String drugTitle;
    private String dosage;
    private String drugType;
    private double tolerance;
    private boolean isFavorite;
    DialogEditDrugBinding binding;
    private ConfirmEditDrugListener confirmEditDrugListener;
    DrugsViewModel viewModel;

    public EditDrugDialogFragment() {
        /* TODO document why this constructor is empty */
    }

    @AssistedInject
    public EditDrugDialogFragment(
            @Assisted("editDrugDialogFragmentArgs") EditDrugDialogFragmentArgs args) {
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_DRUG_ID, args.getDrugId());
        bundle.putString(ARG_DRUG_TITLE, args.getDrugTitle());
        bundle.putString(ARG_DRUG_DOSAGE, args.getDosage());
        bundle.putString(ARG_DRUG_TYPE, args.getDrugType());
        bundle.putDouble(ARG_DRUG_TOLERANCE, args.getTolerance());
        bundle.putBoolean(ARG_DRUG_FAVORITE, args.isFavorite());
        setArguments(bundle);
    }

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            drugId = getArguments().getInt(ARG_DRUG_ID);
            drugTitle = getArguments().getString(ARG_DRUG_TITLE);
            dosage = getArguments().getString(ARG_DRUG_DOSAGE);
            drugType = getArguments().getString(ARG_DRUG_TYPE);
            tolerance = getArguments().getDouble(ARG_DRUG_TOLERANCE);
            isFavorite = getArguments().getBoolean(ARG_DRUG_FAVORITE);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(requireActivity()).get(DrugsViewModel.class);
        binding = DialogEditDrugBinding.inflate(getLayoutInflater());
        setDrugTypeRadioButtons();
        setDrugUnitRadioButtons();
        binding.editDrugNameText.setText(drugTitle);
        binding.editDrugDosageText.setText(dosage);
        binding.editDrugToleranceText.setText(Double.toString(tolerance));
        binding.isFavoriteCheckbox.setChecked(isFavorite);
        return new AlertDialog.Builder(requireContext())
                .setView(binding.getRoot())
                .setTitle(getString(R.string.edit_drug))
                .setPositiveButton(
                        getString(R.string.save),
                        (dialog, id) ->
                                this.confirmEditDrugListener.onConfirmEditDrug(
                                        Objects.requireNonNull(
                                                binding.editDrugNameText.getText()).toString(),
                                        Objects.requireNonNull(
                                                binding.editDrugDosageText.getText()).toString(),
                                        binding.drugTypeRadioGroup.getCheckedRadioButtonId(),
                                        binding.dispenseUnitRadioGroup.getCheckedRadioButtonId(),
                                        Objects.requireNonNull(
                                                binding.editDrugToleranceText.getText()).toString(),
                                        binding.isFavoriteCheckbox.isChecked()))
                .setNegativeButton(getString(R.string.cancel), (dialog, id) -> {
                })
                .create();
    }

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

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setDrugUnitRadioButtons() {
        viewModel.getDrugUnits().observe(this,
                list -> list.forEach(item -> {
                    RadioButton radio = new RadioButton(getContext());
                    radio.setId(item.getUnitId());
                    radio.setText(item.getTitle());
                    // radio.setChecked(dispenseUnit.equals(item.getTitle()));
                    binding.dispenseUnitRadioGroup.addView(radio);
                }));
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        setArguments(null);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.confirmEditDrugListener = null;
        setArguments(null);
    }

    public interface ConfirmEditDrugListener {
        void onConfirmEditDrug(
                String name, String dosage, int drugTypeId, int unitId, String tolerance, boolean isFavorite);
    }
}
