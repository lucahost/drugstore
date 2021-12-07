package ch.ffhs.drugstore.presentation.view.fragment.management;

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

import ch.ffhs.drugstore.data.entity.Drug;
import ch.ffhs.drugstore.databinding.FragmentDrugsBinding;
import ch.ffhs.drugstore.presentation.view.adapter.DrugListAdapter;
import ch.ffhs.drugstore.presentation.viewmodel.DrugsViewModel;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DrugsFragment extends Fragment
    implements DrugListAdapter.OnItemClickListener,
        CreateDrugDialogFragment.ConfirmCreateDrugListener {

  @Inject DrugListAdapter adapter;
  @Inject CreateDrugDialogFragment createDrugDialogFragment;
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
        view ->
            createDrugDialogFragment.show(getChildFragmentManager(), CreateDrugDialogFragment.TAG));
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
    viewModel.getItems().observe(getViewLifecycleOwner(), adapter::submitList);
  }

  public Context context() {
    return Objects.requireNonNull(this.getActivity()).getApplicationContext();
  }

  @Override
  public void onItemClick(Drug drug) {
    // TODO
  }

  private void setupRecyclerView() {
    binding.drugsList.setLayoutManager(new LinearLayoutManager(context()));
    adapter.setClickListener(this);
    binding.drugsList.setAdapter(this.adapter);
  }

  @Override
  public void onConfirmCreateDrug(
      String name, String dosage, String category, String dispenseUnit, String tolerance) {
    Toast.makeText(context(), "Erfolgreich erfasst", Toast.LENGTH_SHORT).show();
  }
}
