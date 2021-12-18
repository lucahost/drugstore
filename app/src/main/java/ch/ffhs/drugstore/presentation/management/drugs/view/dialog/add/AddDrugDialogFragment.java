package ch.ffhs.drugstore.presentation.management.drugs.view.dialog.add;

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
import ch.ffhs.drugstore.databinding.DialogAddDrugBinding;
import ch.ffhs.drugstore.presentation.InputValidation;
import dagger.assisted.Assisted;
import dagger.assisted.AssistedInject;
import dagger.hilt.android.AndroidEntryPoint;

/**
 * Dialog for the addition of stock of a drug.
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
@AndroidEntryPoint
public class AddDrugDialogFragment extends DialogFragment {

    public static final String TAG = "AddDrug";
    public static final String ARG_DRUG_ID = "drugId";
    public static final String ARG_DRUG_TITLE = "drugTitle";
    public static final String ARG_DRUG_DOSAGE = "dosage";
    public static final String ARG_DRUG_UNIT = "unit";
    public static final String ARG_DRUG_STOCK_AMOUNT = "stockAmount";

    private DialogAddDrugBinding binding = null;
    private Integer drugId = null;
    private String drugTitle = null;
    private String dosage = null;
    private String unit = null;
    private Double stockAmount = null;
    private ConfirmAddDrugListener confirmAddDrugListener = null;

    /**
     * Constructs a {@link AddDrugDialogFragment} by {@link AddDrugDialogFragmentFactory}.
     *
     * @param args fragment arguments
     */
    @AssistedInject
    public AddDrugDialogFragment(
            @Assisted("addDrugDialogFragmentArgs") AddDrugDialogFragmentArgs args) {
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_DRUG_ID, args.getDrugId());
        bundle.putString(ARG_DRUG_TITLE, args.getDrugTitle());
        bundle.putString(ARG_DRUG_DOSAGE, args.getDosage());
        bundle.putString(ARG_DRUG_UNIT, args.getUnit());
        bundle.putDouble(ARG_DRUG_STOCK_AMOUNT, args.getStockAmount());
        setArguments(bundle);
    }

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            drugId = getArguments().getInt(ARG_DRUG_ID);
            drugTitle = getArguments().getString(ARG_DRUG_TITLE);
            dosage = getArguments().getString(ARG_DRUG_DOSAGE);
            unit = getArguments().getString(ARG_DRUG_UNIT);
            stockAmount = getArguments().getDouble(ARG_DRUG_STOCK_AMOUNT);
        }
    }

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        binding = DialogAddDrugBinding.inflate(getLayoutInflater());
        binding.drugNameText.setText(drugTitle);
        binding.drugDosageText.setText(dosage);
        String drugStockAmount = String.format(Locale.getDefault(), "%.2f %s", stockAmount, unit);
        binding.drugStockAmountText.setText(drugStockAmount);
        return getAlertDialog();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onDetach() {
        super.onDetach();
        this.confirmAddDrugListener = null;
    }

    /**
     * @return the alert dialog
     */
    @NonNull
    private AlertDialog getAlertDialog() {
        AlertDialog dialog = new AlertDialog.Builder(requireContext())
                .setView(binding.getRoot())
                .setTitle(getString(R.string.add_drug))
                .setPositiveButton(getString(R.string.add), null)
                .setNegativeButton(getString(R.string.cancel), null)
                .create();
        dialog.setOnShowListener(d -> {
            Button button = ((AlertDialog) d).getButton(AlertDialog.BUTTON_POSITIVE);
            button.setOnClickListener(view -> validateInputAndConfirmAddDrug());
        });
        return dialog;
    }

    /**
     * Validates inputs and confirms the adding of drug if there are no validation errors.
     */
    private void validateInputAndConfirmAddDrug() {
        boolean drugCountNotEmpty = InputValidation.validateNumberDecimalStringNotZero(
                binding.drugCountText,
                binding.drugCountTextLayout,
                getString(R.string.error_amount_over_zero_required));

        if (drugCountNotEmpty) {
            String drugCount = Objects.requireNonNull(binding.drugCountText.getText()).toString();

            confirmAddDrugListener.onConfirmAddDrug(drugId, drugCount);
        }
    }
}
