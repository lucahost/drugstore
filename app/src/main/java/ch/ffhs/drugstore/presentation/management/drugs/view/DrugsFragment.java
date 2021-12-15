package ch.ffhs.drugstore.presentation.management.drugs.view;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.Objects;

import javax.inject.Inject;

import ch.ffhs.drugstore.R;
import ch.ffhs.drugstore.databinding.FragmentDrugsBinding;
import ch.ffhs.drugstore.presentation.DialogService;
import ch.ffhs.drugstore.presentation.management.drugs.view.adapter.DrugListAdapter;
import ch.ffhs.drugstore.presentation.management.drugs.view.dialog.AddDrugDialogFragment;
import ch.ffhs.drugstore.presentation.management.drugs.view.dialog.AddDrugDialogFragmentArgs;
import ch.ffhs.drugstore.presentation.management.drugs.view.dialog.CreateDrugDialogFragment;
import ch.ffhs.drugstore.presentation.management.drugs.view.dialog.DeleteDrugDialogFragment;
import ch.ffhs.drugstore.presentation.management.drugs.view.dialog.DeleteDrugDialogFragmentArgs;
import ch.ffhs.drugstore.presentation.management.drugs.view.dialog.EditDrugDialogFragment;
import ch.ffhs.drugstore.presentation.management.drugs.view.dialog.EditDrugDialogFragmentArgs;
import ch.ffhs.drugstore.presentation.management.drugs.view.dialog.RemoveDrugDialogFragment;
import ch.ffhs.drugstore.presentation.management.drugs.view.dialog.RemoveDrugDialogFragmentArgs;
import ch.ffhs.drugstore.presentation.management.drugs.viewmodel.DrugsViewModel;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugDto;
import ch.ffhs.drugstore.shared.exceptions.DrugstoreException;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DrugsFragment extends Fragment
        implements DrugListAdapter.OnItemClickListener,
        CreateDrugDialogFragment.ConfirmCreateDrugListener,
        AddDrugDialogFragment.ConfirmAddDrugListener,
        RemoveDrugDialogFragment.ConfirmRemoveDrugListener,
        EditDrugDialogFragment.ConfirmEditDrugListener,
        DeleteDrugDialogFragment.ConfirmDeleteDrugListener {

    @Inject
    DrugListAdapter adapter;
    @Inject
    DialogService dialogService;
    FragmentDrugsBinding binding;
    DrugsViewModel viewModel;

    @Inject
    public DrugsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDrugsBinding.inflate(getLayoutInflater());
        binding.extendedFab.setOnClickListener(
                view -> dialogService.showCreateDrugDialog(getChildFragmentManager()));
        setupRecyclerView();
        return binding.getRoot();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(DrugsViewModel.class);
        viewModel.getDrugs().observe(getViewLifecycleOwner(), adapter::submitList);
    }

    public Context context() {
        return Objects.requireNonNull(this.getActivity()).getApplicationContext();
    }

    @Override
    public void onItemClick(View view, DrugDto drug) {
        showPopup(view, drug);
    }

    private void showPopup(View v, DrugDto drug) {
        PopupMenu popup = new PopupMenu(Objects.requireNonNull(getContext()), v);
        popup.setOnMenuItemClickListener(getMenuItemClickListener(drug));
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.management_drug_popup_menu, popup.getMenu());
        popup.show();
    }

    @NonNull
    private PopupMenu.OnMenuItemClickListener getMenuItemClickListener(DrugDto drug) {
        return item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.drug_item_add) {
                AddDrugDialogFragmentArgs args = new AddDrugDialogFragmentArgs(drug.getDrugId(),
                        drug.getTitle(), drug.getDosage(),
                        drug.getUnit(), drug.getStockAmount());
                dialogService.showAddDrugDialog(getChildFragmentManager(), args);
                return true;
            } else if (itemId == R.id.drug_item_remove) {
                RemoveDrugDialogFragmentArgs args = new RemoveDrugDialogFragmentArgs(
                        drug.getDrugId(), drug.getTitle(), drug.getDosage(), drug.getUnit(),
                        drug.getStockAmount());
                dialogService.showRemoveDrugDialog(getChildFragmentManager(), args);
                return true;
            } else if (itemId == R.id.drug_item_edit) {
                EditDrugDialogFragmentArgs args = new EditDrugDialogFragmentArgs(
                        drug.getDrugId(), drug.getTitle(), drug.getDosage(), drug.getDrugType(),
                        drug.getUnit(),
                        drug.getTolerance(), drug.isFavorite()
                );
                dialogService.showEditDrugDialog(getChildFragmentManager(), args);
                return true;
            } else if (itemId == R.id.drug_item_delete) {
                DeleteDrugDialogFragmentArgs args = new DeleteDrugDialogFragmentArgs(
                        drug.getDrugId(), drug.getTitle());
                dialogService.showDeleteDrugDialog(getChildFragmentManager(), args);
                return true;
            }
            return false;
        };
    }

    private void setupRecyclerView() {
        binding.drugsList.setLayoutManager(new LinearLayoutManager(context()));
        adapter.setClickListener(this);
        binding.drugsList.setAdapter(this.adapter);
    }

    @Override
    public void onConfirmCreateDrug(
            String name, String dosage, int drugTypeId, int unitId, String tolerance,
            boolean isFavorite) {
        dialogService.dismiss(DialogService.Dialog.CREATE_DRUG);
        try {
            viewModel.createDrug(name, dosage, drugTypeId, unitId, tolerance, isFavorite);
        } catch (Exception ex) {
            new AlertDialog.Builder(getContext())
                    .setTitle(R.string.error_create_drug)
                    .setMessage(ex.getMessage())
                    .setNegativeButton(R.string.close, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
            return;
        }
        Toast.makeText(context(), "Erfolgreich erfasst", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConfirmAddDrug(int drugId, String amount) {
        dialogService.dismiss(DialogService.Dialog.ADD_DRUG);
        try {
            viewModel.updateDrugAmount(drugId, amount);
        } catch (DrugstoreException ex) {
            new AlertDialog.Builder(getContext())
                    .setTitle(R.string.error_update_drug_amount)
                    .setMessage(ex.getCode())
                    .setNegativeButton(R.string.close, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
            return;
        }
        Toast.makeText(context(), "Erfolgreich hinzugefügt", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConfirmDeleteDrug(int drugId) {
        dialogService.dismiss(DialogService.Dialog.DELETE_DRUG);
        viewModel.deleteDrug(drugId);
        Toast.makeText(context(), "Erfolgreich gelöscht", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConfirmEditDrug(int drugId, String name, String dosage, int drugTypeId,
            int unitId, String tolerance, boolean isFavorite) {
        dialogService.dismiss(DialogService.Dialog.EDIT_DRUG);
        viewModel.editDrug(drugId, name, dosage, drugTypeId, unitId, tolerance, isFavorite);
        Toast.makeText(context(), "Erfolgreich bearbeitet", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConfirmRemoveDrug(int drugId, String amount) {
        dialogService.dismiss(DialogService.Dialog.REMOVE_DRUG);
        try {
            viewModel.updateDrugAmount(drugId, "-" + amount);
        } catch (DrugstoreException ex) {
            new AlertDialog.Builder(getContext())
                    .setTitle(R.string.error_update_drug_amount)
                    .setMessage(ex.getCode())
                    .setNegativeButton(R.string.close, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
            return;
        }
        Toast.makeText(context(), "Erfolgreich ausgetragen", Toast.LENGTH_SHORT).show();
    }
}
