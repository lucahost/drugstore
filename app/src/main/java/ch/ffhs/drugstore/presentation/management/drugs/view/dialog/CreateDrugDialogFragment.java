package ch.ffhs.drugstore.presentation.management.drugs.view.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import ch.ffhs.drugstore.R;
import ch.ffhs.drugstore.data.dto.DrugTypeDto;
import ch.ffhs.drugstore.data.dto.UnitDto;
import ch.ffhs.drugstore.data.entity.Unit;
import ch.ffhs.drugstore.databinding.DialogCreateDrugBinding;
import ch.ffhs.drugstore.presentation.management.drugs.viewmodel.DrugsViewModel;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CreateDrugDialogFragment extends DialogFragment {

    public static final String TAG = "CreateDrug";
    DialogCreateDrugBinding binding;
    private ConfirmCreateDrugListener confirmCreateDrugListener;
    DrugsViewModel viewModel;
    ArrayAdapter<String> drugUnitListAdapter;
    ArrayAdapter<String> drugTypeListAdapter;

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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(DrugsViewModel.class);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(requireActivity()).get(DrugsViewModel.class);
        binding = DialogCreateDrugBinding.inflate(getLayoutInflater());
        setDrugUnitDropdownOptions();
        setDrugTypeDropdownOptions();
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
                                        Objects.requireNonNull(
                                                binding.categoryText.getText()).toString(),
                                        Objects.requireNonNull(
                                                binding.dispenseUnitText.getText()).toString(),
                                        Objects.requireNonNull(
                                                binding.toleranceText.getText()).toString()))
                .setNegativeButton(getString(R.string.cancel), (dialog, id) -> {
                })
                .create();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setDrugUnitDropdownOptions() {
        drugUnitListAdapter = new ArrayAdapter<>(requireContext(), R.layout.dropdown_list_item);
        assert getParentFragment() != null;
        viewModel.getDrugUnits().observe(getParentFragment(),
                list -> list.forEach(item -> {
                    if (drugUnitListAdapter.getPosition(item.getTitle()) < 0) {
                        drugUnitListAdapter.add(item.getTitle());
                    }
                }));
        binding.dispenseUnitText.setAdapter(drugUnitListAdapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setDrugTypeDropdownOptions() {
        drugTypeListAdapter = new ArrayAdapter<>(requireContext(), R.layout.dropdown_list_item);
        assert getParentFragment() != null;
        viewModel.getDrugTypes().observe(getParentFragment(),
                list -> list.forEach(item -> {
                    if (drugTypeListAdapter.getPosition(item.getTitle()) < 0) {
                        drugTypeListAdapter.add(item.getTitle());
                    }
                }));
        binding.categoryText.setAdapter(drugTypeListAdapter);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.confirmCreateDrugListener = null;
    }

    public interface ConfirmCreateDrugListener {
        void onConfirmCreateDrug(
                String name, String dosage, String category, String dispenseUnit, String tolerance);
    }
}
