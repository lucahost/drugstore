package ch.ffhs.drugstore.presentation.management.drugs.view.dialog.delete;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import ch.ffhs.drugstore.R;
import ch.ffhs.drugstore.databinding.DialogDeleteDrugBinding;
import dagger.assisted.Assisted;
import dagger.assisted.AssistedInject;
import dagger.hilt.android.AndroidEntryPoint;

/**
 * Dialog for the dispensation of a drug.
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
@AndroidEntryPoint
public class DeleteDrugDialogFragment extends DialogFragment {

    public static final String TAG = "DeleteDrug";
    public static final String ARG_DRUG_ID = "drugId";
    public static final String ARG_DRUG_TITLE = "drugTitle";

    private Integer drugId = null;
    private String drugTitle = null;
    private ConfirmDeleteDrugListener confirmDeleteDrugListener = null;

    /**
     * Constructs a {@link DeleteDrugDialogFragment} by {@link DeleteDrugDialogFragmentFactory}.
     *
     * @param args fragment arguments
     */
    @AssistedInject
    public DeleteDrugDialogFragment(
            @Assisted("deleteDrugDialogFragmentArgs") DeleteDrugDialogFragmentArgs args) {
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_DRUG_ID, args.getDrugId());
        bundle.putString(ARG_DRUG_TITLE, args.getDrugTitle());
        setArguments(bundle);
    }

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            drugId = getArguments().getInt(ARG_DRUG_ID);
            drugTitle = getArguments().getString(ARG_DRUG_TITLE);
        }
    }

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        DialogDeleteDrugBinding binding = DialogDeleteDrugBinding.inflate(getLayoutInflater());
        return new AlertDialog.Builder(requireContext())
                .setView(binding.getRoot())
                .setTitle(getString(R.string.delete_drug, drugTitle))
                .setPositiveButton(
                        getString(R.string.delete),
                        (dialog, id) -> this.confirmDeleteDrugListener.onConfirmDeleteDrug(drugId))
                .setNegativeButton(getString(R.string.cancel), (dialog, id) -> {
                })
                .create();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onDetach() {
        super.onDetach();
        this.confirmDeleteDrugListener = null;
    }
}
