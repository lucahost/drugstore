package ch.ffhs.drugstore.presentation.management.drugs.view.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Locale;
import java.util.Objects;

import ch.ffhs.drugstore.R;
import ch.ffhs.drugstore.databinding.DialogRemoveDrugBinding;
import ch.ffhs.drugstore.presentation.InputValidation;
import dagger.assisted.Assisted;
import dagger.assisted.AssistedInject;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class RemoveDrugDialogFragment extends DialogFragment {

    public static final String TAG = "RemoveDrug";
    public static final String ARG_DRUG_ID = "drugId";
    public static final String ARG_DRUG_TITLE = "drugTitle";
    public static final String ARG_DRUG_DOSAGE = "dosage";
    public static final String ARG_DRUG_UNIT = "unit";
    public static final String ARG_DRUG_STOCKAMOUNT = "stockAmount";
    DialogRemoveDrugBinding binding;
    private int drugId;
    private String drugTitle;
    private String dosage;
    private String unit;
    private double stockAmount;
    private ConfirmRemoveDrugListener confirmRemoveDrugListener;

    public RemoveDrugDialogFragment() {
        /* TODO document why this constructor is empty */
    }

    @AssistedInject
    public RemoveDrugDialogFragment(
            @Assisted("removeDrugDialogFragmentArgs") RemoveDrugDialogFragmentArgs args) {
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_DRUG_ID, args.getDrugId());
        bundle.putString(ARG_DRUG_TITLE, args.getDrugTitle());
        bundle.putString(ARG_DRUG_DOSAGE, args.getDosage());
        bundle.putString(ARG_DRUG_UNIT, args.getUnit());
        bundle.putDouble(ARG_DRUG_STOCKAMOUNT, args.getStockAmount());
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            drugId = getArguments().getInt(ARG_DRUG_ID);
            drugTitle = getArguments().getString(ARG_DRUG_TITLE);
            dosage = getArguments().getString(ARG_DRUG_DOSAGE);
            unit = getArguments().getString(ARG_DRUG_UNIT);
            stockAmount = getArguments().getDouble(ARG_DRUG_STOCKAMOUNT);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        binding = DialogRemoveDrugBinding.inflate(getLayoutInflater());
        binding.drugNameText.setText(drugTitle);
        binding.drugDosageText.setText(dosage);
        String drugStockAmount = String.format(Locale.getDefault(), "%.2f %s", stockAmount, unit);
        binding.drugStockAmountText.setText(drugStockAmount);
        return getAlertDialog();
    }

    @NonNull
    private AlertDialog getAlertDialog() {
        AlertDialog dialog = new AlertDialog.Builder(requireContext())
                .setView(binding.getRoot())
                .setTitle(getString(R.string.remove_drug))
                .setPositiveButton(getString(R.string.remove), null)
                .setNegativeButton(getString(R.string.cancel), null)
                .create();
        dialog.setOnShowListener(d -> {
            Button button = ((AlertDialog) d).getButton(AlertDialog.BUTTON_POSITIVE);
            button.setOnClickListener(view -> validateInputAndConfirmRemoveDrug());
        });
        return dialog;
    }

    private void validateInputAndConfirmRemoveDrug() {
        boolean drugCountNotEmpty = InputValidation.validateNumberDecimalStringNotZero(
                binding.drugCountText,
                binding.drugCountTextLayout,
                getString(R.string.error_amount_over_zero_required));

        if (drugCountNotEmpty) {
            String drugCount = Objects.requireNonNull(binding.drugCountText.getText()).toString();

            confirmRemoveDrugListener.onConfirmRemoveDrug(drugId, drugCount);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.confirmRemoveDrugListener = null;
    }

    public interface ConfirmRemoveDrugListener {
        void onConfirmRemoveDrug(int drugId, String amount);
    }
}
