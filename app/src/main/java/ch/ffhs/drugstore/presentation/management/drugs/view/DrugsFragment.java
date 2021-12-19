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
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Objects;

import javax.inject.Inject;

import ch.ffhs.drugstore.R;
import ch.ffhs.drugstore.databinding.FragmentDrugsBinding;
import ch.ffhs.drugstore.presentation.DialogService;
import ch.ffhs.drugstore.presentation.DialogType;
import ch.ffhs.drugstore.presentation.management.drugs.view.adapter.DrugListAdapter;
import ch.ffhs.drugstore.presentation.management.drugs.view.adapter.OnDrugListItemClickListener;
import ch.ffhs.drugstore.presentation.management.drugs.view.dialog.add.AddDrugDialogFragmentArgs;
import ch.ffhs.drugstore.presentation.management.drugs.view.dialog.add.ConfirmAddDrugListener;
import ch.ffhs.drugstore.presentation.management.drugs.view.dialog.create.ConfirmCreateDrugListener;
import ch.ffhs.drugstore.presentation.management.drugs.view.dialog.delete.ConfirmDeleteDrugListener;
import ch.ffhs.drugstore.presentation.management.drugs.view.dialog.delete.DeleteDrugDialogFragmentArgs;
import ch.ffhs.drugstore.presentation.management.drugs.view.dialog.edit.ConfirmEditDrugListener;
import ch.ffhs.drugstore.presentation.management.drugs.view.dialog.edit.EditDrugDialogFragmentArgs;
import ch.ffhs.drugstore.presentation.management.drugs.view.dialog.remove.ConfirmRemoveDrugListener;
import ch.ffhs.drugstore.presentation.management.drugs.view.dialog.remove.RemoveDrugDialogFragmentArgs;
import ch.ffhs.drugstore.presentation.management.drugs.viewmodel.DrugsViewModel;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugDto;
import ch.ffhs.drugstore.shared.exceptions.DrugstoreException;
import dagger.hilt.android.AndroidEntryPoint;

/**
 * Drugs Fragment
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
@AndroidEntryPoint
public class DrugsFragment extends Fragment
        implements OnDrugListItemClickListener,
        ConfirmCreateDrugListener,
        ConfirmAddDrugListener,
        ConfirmRemoveDrugListener,
        ConfirmEditDrugListener,
        ConfirmDeleteDrugListener {

    @Inject
    protected DrugListAdapter adapter;
    @Inject
    protected DialogService dialogService;
    private FragmentDrugsBinding binding;
    private DrugsViewModel viewModel;

    /**
     * {@inheritDoc}
     */
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDrugsBinding.inflate(getLayoutInflater());
        binding.extendedFab.setOnClickListener(
                view -> dialogService.showCreateDrugDialog(getChildFragmentManager()));
        setupRecyclerView();
        return binding.getRoot();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(DrugsViewModel.class);
        viewModel.getDrugs().observe(getViewLifecycleOwner(), adapter::submitList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onItemClick(View view, DrugDto drug) {
        showPopup(view, drug);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onConfirmCreateDrug(
            String name, String substance, String dosage, int drugTypeId, int unitId,
            String tolerance,
            boolean isFavorite) {
        dialogService.dismiss(DialogType.CREATE_DRUG);
        try {
            viewModel.createDrug(name, substance, dosage, drugTypeId, unitId, tolerance,
                    isFavorite);
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void onConfirmAddDrug(int drugId, String amount) {
        dialogService.dismiss(DialogType.ADD_DRUG);
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void onConfirmDeleteDrug(int drugId) {
        dialogService.dismiss(DialogType.DELETE_DRUG);
        viewModel.deleteDrug(drugId);
        Toast.makeText(context(), "Erfolgreich gelöscht", Toast.LENGTH_SHORT).show();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onConfirmEditDrug(int drugId, String name, String substance, String dosage,
            int drugTypeId,
            int unitId, String tolerance, boolean isFavorite) {
        dialogService.dismiss(DialogType.EDIT_DRUG);
        try {
            viewModel.editDrug(drugId, name, substance, dosage, drugTypeId, unitId, tolerance,
                    isFavorite);
        }
        catch(DrugstoreException ex) {
            new AlertDialog.Builder(getContext())
                    .setTitle(R.string.error_update_drug)
                    .setMessage(ex.getCode())
                    .setNegativeButton(R.string.close, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
            return;
        }

        Toast.makeText(context(), "Erfolgreich bearbeitet", Toast.LENGTH_SHORT).show();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onConfirmRemoveDrug(int drugId, String amount) {
        dialogService.dismiss(DialogType.REMOVE_DRUG);
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

    /**
     * Convenience method to access the app context
     *
     * @return app context
     */
    private Context context() {
        return Objects.requireNonNull(this.getActivity()).getApplicationContext();
    }

    /**
     * Show the menu popup for a specific drug.
     *
     * @param v    view
     * @param drug drug
     */
    private void showPopup(View v, DrugDto drug) {
        PopupMenu popup = new PopupMenu(Objects.requireNonNull(getContext()), v);
        popup.setOnMenuItemClickListener(getMenuItemClickListener(drug));
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.management_drug_popup_menu, popup.getMenu());
        popup.show();
    }

    /**
     * Get the correct click listener for a drug.
     *
     * @param drug the drug to get the click listener
     * @return click listener
     */
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
                        drug.getDrugId(), drug.getTitle(), drug.getSubstance(), drug.getDosage(),
                        drug.getDrugType(),
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

    /**
     * Setup the recycler view list
     */
    private void setupRecyclerView() {
        binding.drugsList.setLayoutManager(
                new GridLayoutManager(context(), 2, RecyclerView.VERTICAL, false));
        binding.drugsList.addItemDecoration(
                new DividerItemDecoration(context(), DividerItemDecoration.VERTICAL));
        adapter.setClickListener(this);
        binding.drugsList.setAdapter(this.adapter);
    }
}
