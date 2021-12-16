package ch.ffhs.drugstore.presentation.management.drugs.view.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Locale;

import ch.ffhs.drugstore.R;
import ch.ffhs.drugstore.databinding.DialogRemoveDrugBinding;
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
        return new AlertDialog.Builder(requireContext())
                .setView(binding.getRoot())
                .setTitle(getString(R.string.remove_drug))
                .setPositiveButton(
                        getString(R.string.remove),
                        (dialog, id) -> this.confirmRemoveDrugListener.onConfirmRemoveDrug(
                                drugId, binding.drugCountText.getText().toString()
                        ))
                .setNegativeButton(getString(R.string.cancel), (dialog, id) -> {
                })
                .create();
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
