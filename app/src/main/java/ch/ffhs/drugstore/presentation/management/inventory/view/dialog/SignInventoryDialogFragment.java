package ch.ffhs.drugstore.presentation.management.inventory.view.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Objects;

import javax.inject.Inject;

import ch.ffhs.drugstore.R;
import ch.ffhs.drugstore.databinding.DialogSignInventoryBinding;
import ch.ffhs.drugstore.presentation.InputValidation;
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
        return getAlertDialog();
    }

    @NonNull
    private AlertDialog getAlertDialog() {
        AlertDialog dialog = new AlertDialog.Builder(requireContext())
                .setView(binding.getRoot())
                .setTitle(getString(R.string.sign))
                .setPositiveButton(getString(R.string.sign), null)
                .setNegativeButton(getString(R.string.cancel), null)
                .create();
        dialog.setOnShowListener(d -> {
            Button button = ((AlertDialog) d).getButton(AlertDialog.BUTTON_POSITIVE);
            button.setOnClickListener(view -> validateInputAndCConfirmSignInventory());
        });
        return dialog;
    }

    private void validateInputAndCConfirmSignInventory() {
        boolean employeeNotEmpty = InputValidation.validateTextNotEmpty(
                binding.employeeText,
                binding.employeeTextLayout, getString(R.string.error_employee_required));

        if (employeeNotEmpty) {
            String employee = Objects.requireNonNull(binding.employeeText.getText()).toString();

            confirmSignInventoryListener.onConfirmSignInventory(employee);
        }
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
