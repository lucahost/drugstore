package ch.ffhs.drugstore.presentation.management.inventory.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.Objects;

import javax.inject.Inject;

import ch.ffhs.drugstore.databinding.FragmentInventoryBinding;
import ch.ffhs.drugstore.presentation.DialogService;
import ch.ffhs.drugstore.presentation.management.inventory.view.adapter.InventoryListAdapter;
import ch.ffhs.drugstore.presentation.management.inventory.view.dialog.SignInventoryDialogFragment;
import ch.ffhs.drugstore.presentation.management.inventory.viewmodel.InventoryViewModel;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugDto;
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
    public void onItemClick(DrugDto inventoryDrug) {
        viewModel.toggleInventoryItem(inventoryDrug.getDrugId());
        Toast.makeText(context(), "Checked", Toast.LENGTH_SHORT).show();
    }

    private void setupRecyclerView() {
        binding.inventoryList.setLayoutManager(new LinearLayoutManager(context()));
        adapter.setClickListener(this);
        binding.inventoryList.setAdapter(this.adapter);
    }

    @Override
    public void onConfirmSignInventory(String employee) {
        dialogService.dismiss(DialogService.Dialog.SIGN_INVENTORY);
        viewModel.signInventory(employee);
        Toast.makeText(context(), "Erfolgreich signiert", Toast.LENGTH_SHORT).show();
    }
}
