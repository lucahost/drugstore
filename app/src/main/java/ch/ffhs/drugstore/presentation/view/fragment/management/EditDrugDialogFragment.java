package ch.ffhs.drugstore.presentation.view.fragment.management;

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
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class EditDrugDialogFragment extends DialogFragment {

  public static final String TAG = "EditDrug";
  DialogEditDrugBinding binding;
  private ConfirmEditDrugListener confirmEditDrugListener;

  @Inject
  public EditDrugDialogFragment() {
    /* TODO document why this constructor is empty */
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
  public View onCreateView(
      @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    binding = DialogEditDrugBinding.inflate(inflater, container, false);
    return binding.getRoot();
  }

  @NonNull
  @Override
  public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
    View addView = View.inflate(getActivity(), R.layout.dialog_edit_drug, null);
    return new AlertDialog.Builder(requireContext())
        .setView(addView)
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
