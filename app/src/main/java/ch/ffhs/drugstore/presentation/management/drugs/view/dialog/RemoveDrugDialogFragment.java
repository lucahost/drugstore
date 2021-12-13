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
import ch.ffhs.drugstore.databinding.DialogDeleteDrugBinding;
import ch.ffhs.drugstore.databinding.DialogRemoveDrugBinding;
import dagger.assisted.Assisted;
import dagger.assisted.AssistedInject;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class RemoveDrugDialogFragment extends DialogFragment {

  public static final String TAG = "RemoveDrug";
  public static final String ARG_DRUG_ID = "drugId";
  public static final String ARG_DRUG_TITLE = "drugTitle";
  DialogRemoveDrugBinding binding;
  private ConfirmRemoveDrugListener confirmRemoveDrugListener;

  public RemoveDrugDialogFragment() {
    /* TODO document why this constructor is empty */
  }

  @AssistedInject
  public RemoveDrugDialogFragment(@Assisted("removeDrugDialogFragmentArgs") RemoveDrugDialogFragmentArgs args) {
    Bundle bundle = new Bundle();
    bundle.putInt(ARG_DRUG_ID, args.getDrugId());
    bundle.putString(ARG_DRUG_TITLE, args.getDrugTitle());
    setArguments(bundle);
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
    binding = DialogRemoveDrugBinding.inflate(getLayoutInflater());
    return new AlertDialog.Builder(requireContext())
        .setView(binding.getRoot())
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
