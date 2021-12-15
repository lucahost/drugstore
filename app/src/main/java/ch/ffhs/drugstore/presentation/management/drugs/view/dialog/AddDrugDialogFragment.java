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
import ch.ffhs.drugstore.databinding.DialogAddDrugBinding;
import ch.ffhs.drugstore.presentation.dispensary.view.dialog.DispenseDrugDialogFragmentArgs;
import dagger.assisted.Assisted;
import dagger.assisted.AssistedInject;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AddDrugDialogFragment extends DialogFragment {

  public static final String TAG = "AddDrug";
  public static final String ARG_DRUG_ID = "drugId";
  public static final String ARG_DRUG_TITLE = "drugTitle";
  public static final String ARG_DRUG_DOSAGE = "dosage";
  public static final String ARG_DRUG_UNIT = "unit";
  private int drugId;
  private String drugTitle;
  private String dosage;
  private String unit;
  DialogAddDrugBinding binding;
  private ConfirmAddDrugListener confirmAddDrugListener;

  public AddDrugDialogFragment() {
    /* TODO document why this constructor is empty */
  }

  @AssistedInject
  public AddDrugDialogFragment(
          @Assisted("addDrugDialogFragmentArgs") AddDrugDialogFragmentArgs args) {
    Bundle bundle = new Bundle();
    bundle.putInt(ARG_DRUG_ID, args.getDrugId());
    bundle.putString(ARG_DRUG_TITLE, args.getDrugTitle());
    bundle.putString(ARG_DRUG_DOSAGE, args.getDosage());
    bundle.putString(ARG_DRUG_UNIT, args.getUnit());
    setArguments(bundle);
  }

  @Override
  public void onAttach(@NonNull Context context) {
    super.onAttach(context);
    if (getParentFragment() instanceof ConfirmAddDrugListener) {
      this.confirmAddDrugListener = (ConfirmAddDrugListener) getParentFragment();
    }
    if (context instanceof ConfirmAddDrugListener) {
      this.confirmAddDrugListener = (ConfirmAddDrugListener) context;
    }
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      drugId = getArguments().getInt(ARG_DRUG_ID);
      drugTitle = getArguments().getString(ARG_DRUG_TITLE);
      dosage = getArguments().getString(ARG_DRUG_DOSAGE);
      unit = getArguments().getString(ARG_DRUG_UNIT);
    }
  }

  @NonNull
  @Override
  public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
    binding = DialogAddDrugBinding.inflate(getLayoutInflater());
    binding.drugNameText.setText(drugTitle);
    binding.drugDosageText.setText(dosage);
    binding.drugUnitText.setText(unit);
    return new AlertDialog.Builder(requireContext())
        .setView(binding.getRoot())
        .setTitle(getString(R.string.add_drug))
        .setPositiveButton(
            getString(R.string.add),
            (dialog, id) ->
                this.confirmAddDrugListener.onConfirmAddDrug(drugId,
                       binding.drugCountText.getText().toString()))
        .setNegativeButton(getString(R.string.cancel), (dialog, id) -> {})
        .create();
  }

  @Override
  public void onDetach() {
    super.onDetach();
    this.confirmAddDrugListener = null;
  }

  public interface ConfirmAddDrugListener {
    void onConfirmAddDrug(int drugId, String amount);
  }
}
