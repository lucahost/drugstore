package ch.ffhs.drugstore.presentation.view.fragment.management;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.Objects;

import javax.inject.Inject;

import ch.ffhs.drugstore.data.entity.InventoryDrug;
import ch.ffhs.drugstore.databinding.FragmentInventoryBinding;
import ch.ffhs.drugstore.presentation.view.adapter.InventoryListAdapter;
import ch.ffhs.drugstore.presentation.viewmodel.InventoryViewModel;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class InventoryFragment extends Fragment
    implements InventoryListAdapter.OnItemClickListener {

  @Inject InventoryListAdapter adapter;
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
  public void onItemClick(InventoryDrug inventoryDrug) {
    // TODO
  }

  @Override
  public void onItemLongClick(InventoryDrug inventoryDrug) {
    // TODO
  }

  private void setupRecyclerView() {
    binding.inventoryList.setLayoutManager(new LinearLayoutManager(context()));
    adapter.setClickListener(this);
    binding.inventoryList.setAdapter(this.adapter);
  }
}
