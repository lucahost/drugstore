package ch.ffhs.drugstore.presentation.management.inventory.view;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Objects;

import javax.inject.Inject;

import ch.ffhs.drugstore.R;
import ch.ffhs.drugstore.databinding.FragmentInventoryBinding;
import ch.ffhs.drugstore.presentation.dialog.DialogService;
import ch.ffhs.drugstore.presentation.dialog.DialogType;
import ch.ffhs.drugstore.presentation.management.inventory.view.adapter.InventoryListAdapter;
import ch.ffhs.drugstore.presentation.management.inventory.view.adapter.OnHistoryItemClickListener;
import ch.ffhs.drugstore.presentation.management.inventory.view.dialog.ConfirmSignInventoryListener;
import ch.ffhs.drugstore.presentation.management.inventory.viewmodel.InventoryViewModel;
import ch.ffhs.drugstore.shared.dto.management.drugs.SelectableDrugDto;
import ch.ffhs.drugstore.shared.exceptions.DrugstoreException;
import dagger.hilt.android.AndroidEntryPoint;

/**
 * Inventory Fragment
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
@AndroidEntryPoint
public class InventoryFragment extends Fragment
        implements OnHistoryItemClickListener, ConfirmSignInventoryListener {

    @Inject
    protected InventoryListAdapter adapter;
    @Inject
    protected DialogService dialogService;
    private FragmentInventoryBinding binding;
    private InventoryViewModel viewModel;
    private Menu menu;

    /**
     * {@inheritDoc}
     */
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentInventoryBinding.inflate(getLayoutInflater());
        binding.extendedFab.setOnClickListener(
                view -> dialogService.showSignInventoryDialog(getChildFragmentManager()));
        setupRecyclerView();
        return binding.getRoot();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(InventoryViewModel.class);
        viewModel.getDrugs().observe(getViewLifecycleOwner(), adapter::submitList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        super.onPrepareOptionsMenu(menu);
        this.menu = menu;
        MenuItem shareItem = menu.findItem(R.id.toolbar_share);
        shareItem.setVisible(true);

        MenuItem checkAllItem = menu.findItem(R.id.toolbar_toggle_check_all);
        checkAllItem.setVisible(true);
        CheckBox checkBox = (CheckBox) checkAllItem.getActionView();
        checkBox.setOnCheckedChangeListener(this::onCheckedChanged);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.toolbar_menu, menu);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.toolbar_share) {
            Toast.makeText(context(), "Shared", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onItemClick(SelectableDrugDto inventoryDrug, boolean selected) {
        try {
            viewModel.toggleInventoryItem(inventoryDrug.getDrugId(), selected);
        } catch (DrugstoreException dse) {
            new AlertDialog.Builder(getContext())
                    .setTitle(R.string.error_toggle_favorite)
                    .setMessage(dse.getMessage())
                    .setNegativeButton(R.string.close, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
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
     * Setup the recycler view list
     */
    private void setupRecyclerView() {
        binding.inventoryList.setLayoutManager(
                new GridLayoutManager(context(), 2, RecyclerView.VERTICAL, false));
        binding.inventoryList.addItemDecoration(
                new DividerItemDecoration(context(), DividerItemDecoration.VERTICAL));
        adapter.setClickListener(this);
        binding.inventoryList.setAdapter(this.adapter);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onConfirmSignInventory(String employee) {
        dialogService.dismiss(DialogType.SIGN_INVENTORY);
        viewModel.signInventory(employee);
        Toast.makeText(context(), R.string.signature_success, Toast.LENGTH_SHORT).show();
        CheckBox checkBox =
                (CheckBox) getMenu().findItem(R.id.toolbar_toggle_check_all).getActionView();
        checkBox.setChecked(false);
        adapter.toggleCheckAll(false);
    }

    public Menu getMenu() {
        return menu;
    }

    private void onCheckedChanged(CompoundButton item, boolean checked) {
        adapter.toggleCheckAll(checked);
    }
}
