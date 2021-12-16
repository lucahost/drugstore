package ch.ffhs.drugstore.presentation.management.drugs.view.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.Objects;

import javax.inject.Inject;

import ch.ffhs.drugstore.R;
import ch.ffhs.drugstore.databinding.DialogCreateDrugBinding;
import ch.ffhs.drugstore.presentation.management.drugs.viewmodel.DrugsViewModel;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CreateDrugDialogFragment extends DialogFragment {

    public static final String TAG = "CreateDrug";
    DialogCreateDrugBinding binding;
    private ConfirmCreateDrugListener confirmCreateDrugListener;
    DrugsViewModel viewModel;

    @Inject
    public CreateDrugDialogFragment() {
        /* TODO document why this constructor is empty */
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (getParentFragment() instanceof ConfirmCreateDrugListener) {
            this.confirmCreateDrugListener = (ConfirmCreateDrugListener) getParentFragment();
        }
        if (context instanceof ConfirmCreateDrugListener) {
            this.confirmCreateDrugListener = (ConfirmCreateDrugListener) context;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(requireActivity()).get(DrugsViewModel.class);
        binding = DialogCreateDrugBinding.inflate(getLayoutInflater());
        setDrugTypeRadioButtons();
        setDrugUnitRadioButtons();
        return new AlertDialog.Builder(requireContext())
                .setView(binding.getRoot())
                .setTitle(getString(R.string.create_drug))
                .setPositiveButton(
                        getString(R.string.create),
                        (dialog, id) ->
                                this.confirmCreateDrugListener.onConfirmCreateDrug(
                                        Objects.requireNonNull(
                                                binding.nameText.getText()).toString(),
                                        Objects.requireNonNull(
                                                binding.dosageText.getText()).toString(),
                                        binding.drugTypeRadioGroup.getCheckedRadioButtonId(),
                                        binding.dispenseUnitRadioGroup.getCheckedRadioButtonId(),
                                        Objects.requireNonNull(
                                                binding.toleranceText.getText()).toString(),
                                        binding.isFavoriteCheckbox.isChecked()
                                        ))
                .setNegativeButton(getString(R.string.cancel), (dialog, id) -> {
                })
                .create();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setDrugTypeRadioButtons() {
        viewModel.getDrugTypes().observe(this,
                list -> list.forEach(item -> {
                    RadioButton radio = new RadioButton(getContext());
                    radio.setId(item.getDrugTypeId());
                    radio.setText(item.getTitle());
                    radio.setChecked(false);
                    binding.drugTypeRadioGroup.addView(radio);
                }));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setDrugUnitRadioButtons() {
        viewModel.getDrugUnits().observe(this,
                list -> list.forEach(item -> {
                    RadioButton radio = new RadioButton(getContext());
                    radio.setId(item.getUnitId());
                    radio.setText(item.getTitle());
                    radio.setChecked(false);
                    binding.dispenseUnitRadioGroup.addView(radio);
                }));
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.confirmCreateDrugListener = null;
    }

    public interface ConfirmCreateDrugListener {
        void onConfirmCreateDrug(
                String name, String dosage, int drugTypeId, int unitId, String tolerance, boolean isFavorite);
    }
}
