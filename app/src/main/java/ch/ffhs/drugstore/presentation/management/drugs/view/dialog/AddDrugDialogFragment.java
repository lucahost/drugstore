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
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AddDrugDialogFragment extends DialogFragment {

  public static final String TAG = "AddDrug";
  DialogAddDrugBinding binding;
  private ConfirmAddDrugListener confirmAddDrugListener;

  @Inject
  public AddDrugDialogFragment() {
    /* TODO document why this constructor is empty */
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
  public View onCreateView(
      @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    binding = DialogAddDrugBinding.inflate(inflater, container, false);
    return binding.getRoot();
  }

  @NonNull
  @Override
  public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
    View addView = View.inflate(getActivity(), R.layout.dialog_add_drug, null);
    return new AlertDialog.Builder(requireContext())
        .setView(addView)
        .setTitle(getString(R.string.add_drug))
        .setPositiveButton(
            getString(R.string.add),
            (dialog, id) ->
                this.confirmAddDrugListener.onConfirmAddDrug(
                    Objects.requireNonNull(binding.drugContentText.getText()).toString(),
                    Objects.requireNonNull(binding.drugUnitText.getText()).toString(),
                    Objects.requireNonNull(binding.drugCountText.getText()).toString()))
        .setNegativeButton(getString(R.string.cancel), (dialog, id) -> {})
        .create();
  }

  @Override
  public void onDetach() {
    super.onDetach();
    this.confirmAddDrugListener = null;
  }

  public interface ConfirmAddDrugListener {
    void onConfirmAddDrug(String content, String unit, String count);
  }
}
