package ch.ffhs.drugstore.presentation.dispensary.view;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Objects;

import javax.inject.Inject;

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
    viewModel.getItems().observe(getViewLifecycleOwner(), adapter::submitList);
    viewModel.getFilterState().observe(getViewLifecycleOwner(), this::setupFilter);
  }

  public Context context() {
    return Objects.requireNonNull(this.getActivity()).getApplicationContext();
  }

  private void setupFilter(FilterState<DispensaryFilters> filterState) {
    setupFilterChips(filterState);
    setupSearchBar(filterState);
  }

  private void setupSearchBar(@NonNull FilterState<DispensaryFilters> filterState) {
    binding.outlinedTextField.setEndIconOnClickListener(this::onSearchBarEndIconClick);
    binding.searchTextField.setText(filterState.getSearchFilter());
    binding.searchTextField.getText();
    binding.searchTextField.setOnEditorActionListener(this::onSearch);
  }

  private void setupFilterChips(@NonNull FilterState<DispensaryFilters> filterState) {
    binding.filterFavorite.setChecked(filterState.isFavorites());
    binding.filterFavorite.setOnCheckedChangeListener((compoundButton, b)
            -> onChipFilterClick(compoundButton, b, DispensaryFilters.FAVORITE));

    binding.filterInjection.setChecked(filterState.getFilters().contains(DispensaryFilters.INJECTION));
    binding.filterInjection.setOnCheckedChangeListener((compoundButton, b)
            -> onChipFilterClick(compoundButton, b, DispensaryFilters.INJECTION));

    binding.filterOral.setChecked(filterState.getFilters().contains(DispensaryFilters.ORAL));
    binding.filterOral.setOnCheckedChangeListener((compoundButton, b)
            -> onChipFilterClick(compoundButton, b, DispensaryFilters.ORAL));

    binding.filterOralLiquid.setChecked(filterState.getFilters().contains(DispensaryFilters.ORAL_LIQUID));
    binding.filterOralLiquid.setOnCheckedChangeListener((compoundButton, b)
            -> onChipFilterClick(compoundButton, b, DispensaryFilters.ORAL_LIQUID));

    binding.filterPlaster.setChecked(filterState.getFilters().contains(DispensaryFilters.PLASTER));
    binding.filterPlaster.setOnCheckedChangeListener((compoundButton, b)
            -> onChipFilterClick(compoundButton, b, DispensaryFilters.PLASTER));
  }


  private boolean onSearch(@NonNull TextView textView, int i, KeyEvent keyEvent) {
    FilterState<DispensaryFilters> currentFilters = viewModel.getFilterState().getValue();
    assert currentFilters != null;
    currentFilters.setSearchFilter(textView.getText().toString());
    viewModel.filter(currentFilters);
    return true;
  }

  private void onSearchBarEndIconClick(View view) {
    FilterState<DispensaryFilters> currentFilters = viewModel.getFilterState().getValue();
    assert currentFilters != null;
    currentFilters.setSearchFilter("");
    viewModel.filter(currentFilters);
  }

  private void onChipFilterClick(CompoundButton buttonView, boolean isChecked, DispensaryFilters filter) {
    buttonView.setChecked(isChecked);
    FilterState<DispensaryFilters> currentFilters = viewModel.getFilterState().getValue();
    assert currentFilters != null;
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
