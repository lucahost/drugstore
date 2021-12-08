package ch.ffhs.drugstore.presentation.dispensary.view.dialog;

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
import ch.ffhs.drugstore.databinding.DialogDispenseDrugBinding;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DispenseDrugDialogFragment extends DialogFragment {

  public static final String TAG = "DispenseDrugDialog";

  private ConfirmDispenseDrugListener confirmDispenseDrugListener;
  DialogDispenseDrugBinding binding;

  @Inject
  public DispenseDrugDialogFragment() {
    /* TODO document why this constructor is empty */
  }

  @Override
  public void onAttach(@NonNull Context context) {
    super.onAttach(context);
    if (getParentFragment() instanceof ConfirmDispenseDrugListener) {
      this.confirmDispenseDrugListener = (ConfirmDispenseDrugListener) getParentFragment();
    }
    if (context instanceof ConfirmDispenseDrugListener) {
      this.confirmDispenseDrugListener = (ConfirmDispenseDrugListener) context;
    }
  }

  @Override
  public View onCreateView(
      @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    binding = DialogDispenseDrugBinding.inflate(inflater, container, false);
    return binding.getRoot();
  }

  @NonNull
  @Override
  public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
    View addView = View.inflate(getActivity(), R.layout.dialog_dispense_drug, null);
    return new AlertDialog.Builder(requireContext())
        .setView(addView)
        .setTitle(getString(R.string.dispense))
        .setPositiveButton(
            getString(R.string.dispense),
            (dialog, id) ->
                this.confirmDispenseDrugListener.onConfirmDispenseDrug(
                    Objects.requireNonNull(binding.employeeText.getText()).toString(),
                    Objects.requireNonNull(binding.patientText.getText()).toString(),
                    Objects.requireNonNull(binding.dosageText.getText()).toString()))
        .setNegativeButton(getString(R.string.cancel), (dialog, id) -> {})
        .create();
  }

  @Override
  public void onDetach() {
    super.onDetach();
    this.confirmDispenseDrugListener = null;
  }

  public interface ConfirmDispenseDrugListener {
    void onConfirmDispenseDrug(String employee, String patient, String dosage);
  }
}
