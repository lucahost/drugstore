package ch.ffhs.drugstore.presentation.management.history.view;

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

import ch.ffhs.drugstore.data.entity.Transaction;
import ch.ffhs.drugstore.databinding.FragmentHistoryBinding;
import ch.ffhs.drugstore.presentation.management.history.view.adapter.HistoryListAdapter;
import ch.ffhs.drugstore.presentation.management.history.viewmodel.HistoryViewModel;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HistoryFragment extends Fragment implements HistoryListAdapter.OnItemClickListener {

  @Inject HistoryListAdapter adapter;
  FragmentHistoryBinding binding;
  HistoryViewModel viewModel;

  @Inject
  public HistoryFragment() {
    // Required empty public constructor
  }

  @Override
  public View onCreateView(
      @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    binding = FragmentHistoryBinding.inflate(getLayoutInflater());
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
    viewModel = new ViewModelProvider(requireActivity()).get(HistoryViewModel.class);
    viewModel.getItems().observe(getViewLifecycleOwner(), adapter::submitList);
  }

  public Context context() {
    return Objects.requireNonNull(this.getActivity()).getApplicationContext();
  }

  @Override
  public void onItemClick(Transaction transaction) {
    // TODO
  }

  @Override
  public void onItemLongClick(Transaction transaction) {
    // TODO
  }

  private void setupRecyclerView() {
    binding.historyList.setLayoutManager(new LinearLayoutManager(context()));
    adapter.setClickListener(this);
    binding.historyList.setAdapter(this.adapter);
  }
}
