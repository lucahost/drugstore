package ch.ffhs.drugstore.presentation.management.drugs.view.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Objects;

import javax.inject.Inject;

import ch.ffhs.drugstore.R;
import ch.ffhs.drugstore.databinding.DialogEditDrugBinding;
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
  private int drugId;
  private String drugTitle;
  private String dosage;
  private String drugType;
  private double tolerance;
  DialogEditDrugBinding binding;
  private ConfirmEditDrugListener confirmEditDrugListener;

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
    }
  }

  @NonNull
  @Override
  public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
    binding = DialogEditDrugBinding.inflate(getLayoutInflater());
    binding.editDrugNameText.setText(drugTitle);
    binding.editDrugDosageText.setText(dosage);
    binding.editDrugCategoryText.setText(drugType);
    binding.editDrugDispenseUnitText.setText("TODO");
    binding.editDrugToleranceText.setText(Double.toString(tolerance));
    return new AlertDialog.Builder(requireContext())
        .setView(binding.getRoot())
        .setTitle(getString(R.string.edit_drug))
        .setPositiveButton(
            getString(R.string.save),
            (dialog, id) ->
                this.confirmEditDrugListener.onConfirmEditDrug(
                    Objects.requireNonNull(binding.editDrugNameText.getText()).toString(),
                    Objects.requireNonNull(binding.editDrugDosageText.getText()).toString(),
                    Objects.requireNonNull(binding.editDrugCategoryText.getText()).toString(),
                    Objects.requireNonNull(binding.editDrugDispenseUnitText.getText()).toString(),
                    Objects.requireNonNull(binding.editDrugToleranceText.getText()).toString()))
        .setNegativeButton(getString(R.string.cancel), (dialog, id) -> {})
        .create();
  }

  @Override
  public void onDetach() {
    super.onDetach();
    this.confirmEditDrugListener = null;
  }

  public interface ConfirmEditDrugListener {
    void onConfirmEditDrug(
        String name, String dosage, String category, String dispenseUnit, String tolerance);
  }
}
