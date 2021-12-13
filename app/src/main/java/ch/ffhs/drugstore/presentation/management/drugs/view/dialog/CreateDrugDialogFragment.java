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
import ch.ffhs.drugstore.databinding.DialogCreateDrugBinding;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CreateDrugDialogFragment extends DialogFragment {

  public static final String TAG = "CreateDrug";
  DialogCreateDrugBinding binding;
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

  @Override
  public View onCreateView(
      @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    binding = DialogCreateDrugBinding.inflate(inflater, container, false);
    return binding.getRoot();
  }

  @NonNull
  @Override
  public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
    binding = DialogCreateDrugBinding.inflate(getLayoutInflater());
    return new AlertDialog.Builder(requireContext())
        .setView(binding.getRoot())
        .setTitle(getString(R.string.create_drug))
        .setPositiveButton(
            getString(R.string.create),
            (dialog, id) ->
                this.confirmCreateDrugListener.onConfirmCreateDrug(
                    Objects.requireNonNull(binding.nameText.getText()).toString(),
                    Objects.requireNonNull(binding.dosageText.getText()).toString(),
                    Objects.requireNonNull(binding.categoryText.getText()).toString(),
                    Objects.requireNonNull(binding.dispenseUnitText.getText()).toString(),
                    Objects.requireNonNull(binding.toleranceText.getText()).toString()))
        .setNegativeButton(getString(R.string.cancel), (dialog, id) -> {})
        .create();
  }

  @Override
  public void onDetach() {
    super.onDetach();
    this.confirmCreateDrugListener = null;
  }

  public interface ConfirmCreateDrugListener {
    void onConfirmCreateDrug(
        String name, String dosage, String category, String dispenseUnit, String tolerance);
  }
}
