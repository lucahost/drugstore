package ch.ffhs.drugstore.presentation.dispensary.view.dialog;

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
import ch.ffhs.drugstore.databinding.DialogDispenseDrugBinding;
import ch.ffhs.drugstore.databinding.DialogSignInventoryBinding;
import dagger.assisted.Assisted;
import dagger.assisted.AssistedInject;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DispenseDrugDialogFragment extends DialogFragment {

    public static final String TAG = "DispenseDrugDialog";
    public static final String ARG_DRUG_ID = "drugId";
    public static final String ARG_DRUG_TITLE = "drugTitle";
    public static final String ARG_DRUG_DOSAGE = "drugDosage";

    private ConfirmDispenseDrugListener confirmDispenseDrugListener;
    private DialogDispenseDrugBinding binding;
    private int drugId;
    private String drugTitle;
    private String drugDosage;

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
        setArguments(bundle);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            drugId = getArguments().getInt(ARG_DRUG_ID);
            drugTitle = getArguments().getString(ARG_DRUG_TITLE);
            drugDosage = getArguments().getString(ARG_DRUG_DOSAGE);
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

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DialogDispenseDrugBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        binding = DialogDispenseDrugBinding.inflate(getLayoutInflater());
        binding.drugDosageText.setText(drugDosage);
        return new AlertDialog.Builder(requireContext())
                .setView(binding.getRoot())
                .setTitle(drugTitle)
                .setPositiveButton(
                        getString(R.string.dispense),
                        (dialog, id) ->
                                this.confirmDispenseDrugListener.onConfirmDispenseDrug(
                                        Objects.requireNonNull(
                                                binding.employeeText.getText()).toString(),
                                        Objects.requireNonNull(
                                                binding.patientText.getText()).toString(),
                                        Objects.requireNonNull(
                                                binding.dosageText.getText()).toString()))
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
        void onConfirmDispenseDrug(String employee, String patient, String dosage);
    }
}
