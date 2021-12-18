package ch.ffhs.drugstore.presentation.management.inventory.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import ch.ffhs.drugstore.R;
import ch.ffhs.drugstore.databinding.FragmentInventoryBinding;
import ch.ffhs.drugstore.presentation.DialogService;
import ch.ffhs.drugstore.presentation.management.inventory.view.adapter.InventoryListAdapter;
import ch.ffhs.drugstore.presentation.management.inventory.view.dialog.SignInventoryDialogFragment;
import ch.ffhs.drugstore.presentation.management.inventory.viewmodel.InventoryViewModel;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugDto;
import ch.ffhs.drugstore.shared.exceptions.DrugstoreException;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class InventoryFragment extends Fragment
        implements InventoryListAdapter.OnItemClickListener,
        SignInventoryDialogFragment.ConfirmSignInventoryListener {

    @Inject
    InventoryListAdapter adapter;
    @Inject
    DialogService dialogService;
    FragmentInventoryBinding binding;
    InventoryViewModel viewModel;

    @Inject
    public InventoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentInventoryBinding.inflate(getLayoutInflater());
        binding.extendedFab.setOnClickListener(
                view -> dialogService.showSignInventoryDialog(getChildFragmentManager()));
        setupRecyclerView();
        return binding.getRoot();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(InventoryViewModel.class);
        viewModel.getItems().observe(getViewLifecycleOwner(), adapter::submitList);
    }

    public Context context() {
        return Objects.requireNonNull(this.getActivity()).getApplicationContext();
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        super.onPrepareOptionsMenu(menu);
        MenuItem shareItem = menu.findItem(R.id.toolbar_share);
        shareItem.setVisible(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.toolbar_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.toolbar_share) {
            // don't use getValue()
            // share(viewModel.getItems().getValue());
            Toast.makeText(context(), "Shared", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void share(List<DrugDto> drugDtoList) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        // TODO: write inventory to e.g. csv file
        sendIntent.putExtra(Intent.EXTRA_TEXT, "This should contain the inventory.");
        sendIntent.setType("text/plain");

        Intent shareIntent = Intent.createChooser(sendIntent, null);
        startActivity(shareIntent);
    }

    @Override
    public void onItemClick(DrugDto inventoryDrug) {
        try {

            viewModel.toggleInventoryItem(inventoryDrug.getDrugId());
            Toast.makeText(context(), "Checked", Toast.LENGTH_SHORT).show();
        } catch (DrugstoreException dse) {
            new AlertDialog.Builder(getContext())
                    .setTitle(R.string.error_toggle_favorite)
                    .setMessage(dse.getMessage())
                    .setNegativeButton(R.string.close, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
            return;
        }
    }


    private void setupRecyclerView() {
        binding.inventoryList.setLayoutManager(
                new GridLayoutManager(context(), 2, RecyclerView.VERTICAL, false));
        binding.inventoryList.addItemDecoration(
                new DividerItemDecoration(context(), DividerItemDecoration.VERTICAL));
        adapter.setClickListener(this);
        binding.inventoryList.setAdapter(this.adapter);
    }

    @Override
    public void onConfirmSignInventory(String employee) {
        dialogService.dismiss(DialogService.Dialog.SIGN_INVENTORY);
        viewModel.signInventory(employee);
        Toast.makeText(context(), R.string.signature_success, Toast.LENGTH_SHORT).show();
        adapter.resetCheckState();
    }
}
