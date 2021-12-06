package ch.ffhs.drugstore.presentation.view.fragment.dispensary;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Objects;

import javax.inject.Inject;

import ch.ffhs.drugstore.databinding.FragmentDispensaryBinding;
import ch.ffhs.drugstore.presentation.view.adapter.DrugListAdapter;
import ch.ffhs.drugstore.presentation.viewmodel.DispensaryViewModel;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DispensaryFragment extends Fragment {

  @Inject
  DrugListAdapter adapter;
  FragmentDispensaryBinding binding;
  DispensaryViewModel viewModel;

  @Inject
  public DispensaryFragment() {
    // Required empty public constructor
  }

  @Override
  public View onCreateView(
          @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    binding = FragmentDispensaryBinding.inflate(getLayoutInflater());
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
    viewModel = new ViewModelProvider(requireActivity()).get(DispensaryViewModel.class);
    viewModel.getDrugs().observe(getViewLifecycleOwner(), adapter::submitList);
  }

  public Context context() {
    return Objects.requireNonNull(this.getActivity()).getApplicationContext();
  }

  private void setupRecyclerView() {
    binding.drugs.setLayoutManager(new GridLayoutManager(context(),2, RecyclerView.VERTICAL, false));
    binding.drugs.setAdapter(this.adapter);
  }
}
