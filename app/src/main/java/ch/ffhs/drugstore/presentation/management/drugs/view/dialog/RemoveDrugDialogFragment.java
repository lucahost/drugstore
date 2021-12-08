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

import javax.inject.Inject;

import ch.ffhs.drugstore.R;
import ch.ffhs.drugstore.databinding.DialogRemoveDrugBinding;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class RemoveDrugDialogFragment extends DialogFragment {

  public static final String TAG = "RemoveDrug";
  DialogRemoveDrugBinding binding;
  private ConfirmRemoveDrugListener confirmRemoveDrugListener;

  @Inject
  public RemoveDrugDialogFragment() {
    /* TODO document why this constructor is empty */
  }

  @Override
  public void onAttach(@NonNull Context context) {
    super.onAttach(context);
    if (getParentFragment() instanceof ConfirmRemoveDrugListener) {
      this.confirmRemoveDrugListener = (ConfirmRemoveDrugListener) getParentFragment();
    }
    if (context instanceof ConfirmRemoveDrugListener) {
      this.confirmRemoveDrugListener = (ConfirmRemoveDrugListener) context;
    }
  }

  @Override
  public View onCreateView(
      @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    binding = DialogRemoveDrugBinding.inflate(inflater, container, false);
    return binding.getRoot();
  }

  @NonNull
  @Override
  public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
    View addView = View.inflate(getActivity(), R.layout.dialog_remove_drug, null);
    return new AlertDialog.Builder(requireContext())
        .setView(addView)
        .setTitle(getString(R.string.remove_drug))
        .setPositiveButton(
            getString(R.string.remove),
            (dialog, id) -> this.confirmRemoveDrugListener.onConfirmRemoveDrug())
        .setNegativeButton(getString(R.string.cancel), (dialog, id) -> {})
        .create();
  }

  @Override
  public void onDetach() {
    super.onDetach();
    this.confirmRemoveDrugListener = null;
  }

  public interface ConfirmRemoveDrugListener {
    void onConfirmRemoveDrug();
  }
}
