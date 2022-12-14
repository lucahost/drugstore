package ch.ffhs.drugstore.presentation.dispensary.view.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Objects;

import ch.ffhs.drugstore.R;
import ch.ffhs.drugstore.databinding.DialogDispenseDrugBinding;
import dagger.assisted.Assisted;
import dagger.assisted.AssistedInject;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DispenseDrugDialogFragment extends DialogFragment {

    public static final String TAG = "DispenseDrugDialog";
    public static final String ARG_DRUG_ID = "drugId";
    public static final String ARG_DRUG_TITLE = "drugTitle";
    public static final String ARG_DRUG_DOSAGE = "drugDosage";
    public static final String ARG_DRUG_UNIT = "drugUnit";

    private ConfirmDispenseDrugListener confirmDispenseDrugListener;
    private DialogDispenseDrugBinding binding;
    private int drugId;
    private String drugTitle;
    private String drugDosage;
    private String drugUnit;

    public DispenseDrugDialogFragment() {
        // Required empty public constructor
    }

    @AssistedInject
    public DispenseDrugDialogFragment(
            @Assisted("dispenseDrugDialogFragmentArgs") DispenseDrugDialogFragmentArgs args) {
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_DRUG_ID, args.getDrugId());
        bundle.putString(ARG_DRUG_TITLE, args.getDrugTitle());
        bundle.putString(ARG_DRUG_DOSAGE, args.getDrugDosage());
        bundle.putString(ARG_DRUG_UNIT, args.getDrugUnit());
        setArguments(bundle);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            drugId = getArguments().getInt(ARG_DRUG_ID);
            drugTitle = getArguments().getString(ARG_DRUG_TITLE);
            drugDosage = getArguments().getString(ARG_DRUG_DOSAGE);
            drugUnit = getArguments().getString(ARG_DRUG_UNIT);
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (getParentFragment() instanceof ConfirmDispenseDrugListener) {
            this.confirmDispenseDrugListener = (ConfirmDispenseDrugListener) getParentFragment();
        }
        if (context instanceof ConfirmDispenseDrugListener) {
            this.confirmDispenseDrugListener = (ConfirmDispenseDrugListener) context;
        }
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        binding = DialogDispenseDrugBinding.inflate(getLayoutInflater());
        binding.drugDosageText.setText(drugDosage);
        binding.drugUnitText.setText(drugUnit);
        return new AlertDialog.Builder(requireContext())
                .setView(binding.getRoot())
                .setTitle(drugTitle)
                .setPositiveButton(
                        getString(R.string.dispense),
                        (dialog, id) ->
                                this.confirmDispenseDrugListener.onConfirmDispenseDrug(
                                        drugId,
                                        Objects.requireNonNull(
                                                binding.employeeText.getText()).toString(),
                                        Objects.requireNonNull(
                                                binding.patientText.getText()).toString(),
                                        Objects.requireNonNull(
                                                binding.amountText.getText()).toString()))
                .setNegativeButton(getString(R.string.cancel), (dialog, id) -> {
                })
                .create();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.confirmDispenseDrugListener = null;
    }

    public interface ConfirmDispenseDrugListener {
        void onConfirmDispenseDrug(int drugId, String employee, String patient, String dosage);
    }
}
