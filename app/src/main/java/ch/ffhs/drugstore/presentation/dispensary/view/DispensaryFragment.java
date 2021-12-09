package ch.ffhs.drugstore.presentation.dispensary.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;

import java.util.Objects;

import javax.inject.Inject;

import ch.ffhs.drugstore.R;
import ch.ffhs.drugstore.data.dto.DrugDto;
import ch.ffhs.drugstore.databinding.FragmentDispensaryBinding;
import ch.ffhs.drugstore.presentation.DialogService;
import ch.ffhs.drugstore.presentation.dispensary.view.adapter.DispensaryListAdapter;
import ch.ffhs.drugstore.presentation.dispensary.view.dialog.DispenseDrugDialogFragment;
import ch.ffhs.drugstore.presentation.dispensary.viewmodel.DispensaryViewModel;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DispensaryFragment extends Fragment
    implements DispensaryListAdapter.OnItemClickListener,
        DispenseDrugDialogFragment.ConfirmDispenseDrugListener {

  @Inject DispensaryListAdapter adapter;
  @Inject DialogService dialogService;
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
    setupFilterChips(inflater);
    return binding.getRoot();
  }

  private void setupFilterChips(@NonNull LayoutInflater inflater) {
    for (DispensaryFilters filter : DispensaryFilters.values()) {
      setupFilterChip(inflater, filter);
    }
  }

  private void setupFilterChip(@NonNull LayoutInflater inflater, DispensaryFilters filter) {
    Chip chip = (Chip) getLayoutInflater().inflate(R.layout.filter_chip, binding.chipGroup, false);
    chip.setId(ViewCompat.generateViewId());
    if (filter == DispensaryFilters.FAVORITE) {
      chip.setText("Favorite");
      chip.setOnCheckedChangeListener((compoundButton, b)
              -> onFilterClick(compoundButton, b, DispensaryFilters.FAVORITE));
    } else if (filter == DispensaryFilters.INJECTION) {
      chip.setText("Injektion");
      chip.setOnCheckedChangeListener((compoundButton, b)
              -> onFilterClick(compoundButton, b, DispensaryFilters.INJECTION));
    } else if (filter == DispensaryFilters.ORAL) {
      chip.setText("Oral");
      chip.setOnCheckedChangeListener((compoundButton, b)
              -> onFilterClick(compoundButton, b, DispensaryFilters.ORAL));
    } else if (filter == DispensaryFilters.ORAL_LIQUID) {
      chip.setText("Oral flüssig");
      chip.setOnCheckedChangeListener((compoundButton, b)
              -> onFilterClick(compoundButton, b, DispensaryFilters.ORAL_LIQUID));
    } else if (filter == DispensaryFilters.PLASTER) {
      chip.setText("Pflaster");
      chip.setOnCheckedChangeListener((compoundButton, b)
              -> onFilterClick(compoundButton, b, DispensaryFilters.PLASTER));
    }
    binding.chipGroup.addView(chip);
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    viewModel = new ViewModelProvider(requireActivity()).get(DispensaryViewModel.class);
    viewModel.getItems().observe(getViewLifecycleOwner(), adapter::submitList);
  }

  public Context context() {
    return Objects.requireNonNull(this.getActivity()).getApplicationContext();
  }

  public void onFilterClick(CompoundButton buttonView, boolean isChecked, DispensaryFilters filter) {
    buttonView.setChecked(isChecked);
    FilterState<DispensaryFilters> currentFilters = viewModel.getFilterState();
    if (filter.equals(DispensaryFilters.FAVORITE)) {
      currentFilters.toggleFavorites();
    } else {
      currentFilters.toggleFilter(filter);
    }
    viewModel.filter(currentFilters);
  }

  @Override
  public void onItemClick(DrugDto drug) {
    dialogService.show(getChildFragmentManager(), DialogService.Dialog.DISPENSE_DRUG);
  }

  @Override
  public void onItemLongClick(DrugDto drug) {
    viewModel.addToFavorites();
    Toast.makeText(context(), "Zu Favoriten hinzugefügt", Toast.LENGTH_SHORT).show();
  }

  private void setupRecyclerView() {
    binding.dispensaryList.setLayoutManager(
        new GridLayoutManager(context(), 2, RecyclerView.VERTICAL, false));
    adapter.setClickListener(this);
    binding.dispensaryList.setAdapter(this.adapter);
  }

  @Override
  public void onConfirmDispenseDrug(String employee, String patient, String dosage) {
    dialogService.dismiss(DialogService.Dialog.DISPENSE_DRUG);
    viewModel.dispenseDrug();
    Toast.makeText(context(), "Ausgegeben", Toast.LENGTH_SHORT).show();
  }
}
