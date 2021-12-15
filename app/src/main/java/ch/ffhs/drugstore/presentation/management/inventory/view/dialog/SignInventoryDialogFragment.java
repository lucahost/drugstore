package ch.ffhs.drugstore.presentation.management.inventory.view.dialog;

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
import ch.ffhs.drugstore.databinding.DialogRemoveDrugBinding;
import ch.ffhs.drugstore.databinding.DialogSignInventoryBinding;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SignInventoryDialogFragment extends DialogFragment {

  public static final String TAG = "DispenseDrugDialog";
  DialogSignInventoryBinding binding;
  private ConfirmSignInventoryListener confirmSignInventoryListener;

  @Inject
  public SignInventoryDialogFragment() {
    /* TODO document why this constructor is empty */
  }

  @Override
  public void onAttach(@NonNull Context context) {
    super.onAttach(context);
    if (getParentFragment() instanceof ConfirmSignInventoryListener) {
      this.confirmSignInventoryListener = (ConfirmSignInventoryListener) getParentFragment();
    }
    if (context instanceof ConfirmSignInventoryListener) {
      this.confirmSignInventoryListener = (ConfirmSignInventoryListener) context;
    }
  }

  @NonNull
  @Override
  public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
    binding = DialogSignInventoryBinding.inflate(getLayoutInflater());
    return new AlertDialog.Builder(requireContext())
        .setView(binding.getRoot())
        .setTitle(getString(R.string.sign))
        .setPositiveButton(
            getString(R.string.sign),
            (dialog, id) ->
                this.confirmSignInventoryListener.onConfirmSignInventory(
                    Objects.requireNonNull(binding.employeeText.getText()).toString()))
        .setNegativeButton(getString(R.string.cancel), (dialog, id) -> {})
        .create();
  }

  @Override
  public void onDetach() {
    super.onDetach();
    this.confirmSignInventoryListener = null;
  }

  public interface ConfirmSignInventoryListener {
    void onConfirmSignInventory(String employee);
  }
}
