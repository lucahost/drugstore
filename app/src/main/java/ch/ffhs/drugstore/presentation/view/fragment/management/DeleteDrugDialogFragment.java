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

import javax.inject.Inject;

import ch.ffhs.drugstore.R;
import ch.ffhs.drugstore.databinding.DialogDeleteDrugBinding;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DeleteDrugDialogFragment extends DialogFragment {

  public static final String TAG = "DeleteDrug";
  DialogDeleteDrugBinding binding;
  private ConfirmDeleteDrugListener confirmDeleteDrugListener;

  @Inject
  public DeleteDrugDialogFragment() {
    /* TODO document why this constructor is empty */
  }

  @Override
  public void onAttach(@NonNull Context context) {
    super.onAttach(context);
    if (getParentFragment() instanceof ConfirmDeleteDrugListener) {
      this.confirmDeleteDrugListener = (ConfirmDeleteDrugListener) getParentFragment();
    }
    if (context instanceof ConfirmDeleteDrugListener) {
      this.confirmDeleteDrugListener = (ConfirmDeleteDrugListener) context;
    }
  }

  @Override
  public View onCreateView(
      @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    binding = DialogDeleteDrugBinding.inflate(inflater, container, false);
    return binding.getRoot();
  }

  @NonNull
  @Override
  public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
    View addView = View.inflate(getActivity(), R.layout.dialog_delete_drug, null);
    return new AlertDialog.Builder(requireContext())
        .setView(addView)
        .setTitle(getString(R.string.delete_drug))
        .setPositiveButton(
            getString(R.string.delete),
            (dialog, id) -> this.confirmDeleteDrugListener.onConfirmDeleteDrug(1))
        .setNegativeButton(getString(R.string.cancel), (dialog, id) -> {})
        .create();
  }

  @Override
  public void onDetach() {
    super.onDetach();
    this.confirmDeleteDrugListener = null;
  }

  public interface ConfirmDeleteDrugListener {
    void onConfirmDeleteDrug(int drugId);
  }
}
