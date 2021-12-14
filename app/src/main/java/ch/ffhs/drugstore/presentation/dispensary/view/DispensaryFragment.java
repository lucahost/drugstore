package ch.ffhs.drugstore.presentation.dispensary.view;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import ch.ffhs.drugstore.R;
import ch.ffhs.drugstore.databinding.FragmentDispensaryBinding;
import ch.ffhs.drugstore.presentation.DialogService;
import ch.ffhs.drugstore.presentation.dispensary.view.adapter.DispensaryListAdapter;
import ch.ffhs.drugstore.presentation.dispensary.view.dialog.DispenseDrugDialogFragment;
import ch.ffhs.drugstore.presentation.dispensary.view.dialog.DispenseDrugDialogFragmentArgs;
import ch.ffhs.drugstore.presentation.dispensary.viewmodel.DispensaryViewModel;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugDto;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DispensaryFragment extends Fragment
        implements DispensaryListAdapter.OnItemClickListener,
        DispenseDrugDialogFragment.ConfirmDispenseDrugListener, SearchView.OnQueryTextListener {

    @Inject
    DispensaryListAdapter adapter;
    @Inject
    DialogService dialogService;
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
        viewModel.getItems().observe(getViewLifecycleOwner(), this::observeItems);
        viewModel.getFilterState().observe(getViewLifecycleOwner(), this::setupFilterChips);
        setupSearchBar();
    }

    private void observeItems(List<DrugDto> drugDtos) {
        adapter.submitList(drugDtos);
        if (!drugDtos.isEmpty()) {
            binding.dispensaryList.setVisibility(View.VISIBLE);
            binding.noItems.setVisibility(View.GONE);
        } else {
            binding.dispensaryList.setVisibility(View.GONE);
            binding.noItems.setVisibility(View.VISIBLE);
        }
    }

    public Context context() {
        return Objects.requireNonNull(this.getActivity()).getApplicationContext();
    }

    private void setupSearchBar() {
        binding.searchView.setOnQueryTextListener(this);
        binding.searchView.onActionViewExpanded();
        binding.searchView.clearFocus();
        ImageView clearButton = binding.searchView.findViewById(
                androidx.appcompat.R.id.search_close_btn);
        clearButton.setOnClickListener(v -> {
            binding.searchView.setQuery("", false);
            binding.searchView.clearFocus();
        });
    }

    private void setupFilterChips(@NonNull FilterState<DispensaryFilters> filterState) {
        binding.filterFavorite.setChecked(filterState.isFavorites());
        binding.filterFavorite.setOnCheckedChangeListener((compoundButton, b)
                -> onChipFilterClick(compoundButton, b, DispensaryFilters.FAVORITE));

        binding.filterInjection.setChecked(
                filterState.getFilters().contains(DispensaryFilters.INJECTION));
        binding.filterInjection.setOnCheckedChangeListener((compoundButton, b)
                -> onChipFilterClick(compoundButton, b, DispensaryFilters.INJECTION));

        binding.filterOral.setChecked(filterState.getFilters().contains(DispensaryFilters.ORAL));
        binding.filterOral.setOnCheckedChangeListener((compoundButton, b)
                -> onChipFilterClick(compoundButton, b, DispensaryFilters.ORAL));

        binding.filterOralLiquid.setChecked(
                filterState.getFilters().contains(DispensaryFilters.ORAL_LIQUID));
        binding.filterOralLiquid.setOnCheckedChangeListener((compoundButton, b)
                -> onChipFilterClick(compoundButton, b, DispensaryFilters.ORAL_LIQUID));

        binding.filterPlaster.setChecked(
                filterState.getFilters().contains(DispensaryFilters.PLASTER));
        binding.filterPlaster.setOnCheckedChangeListener((compoundButton, b)
                -> onChipFilterClick(compoundButton, b, DispensaryFilters.PLASTER));
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return search(query);
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return search(newText);
    }

    private boolean search(String searchTerm) {
        FilterState<DispensaryFilters> currentFilters = viewModel.getFilterState().getValue();
        assert currentFilters != null;
        currentFilters.setSearchFilter(searchTerm);
        viewModel.filter(currentFilters);
        return true;
    }

    private void onChipFilterClick(CompoundButton buttonView, boolean isChecked,
            DispensaryFilters filter) {
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
        DispenseDrugDialogFragmentArgs args = new DispenseDrugDialogFragmentArgs(
                drug.getDrugId(), drug.getTitle(), drug.getDosage());
        dialogService.showDispenseDrugDialog(getChildFragmentManager(), args);
    }

    @Override
    public void onItemLongClick(DrugDto drug) {
        viewModel.addToFavorites();
        Toast.makeText(context(), R.string.added_to_favorites, Toast.LENGTH_SHORT).show();
    }

    private void setupRecyclerView() {
        binding.dispensaryList.setLayoutManager(
                new GridLayoutManager(context(), 2, RecyclerView.VERTICAL, false));
        adapter.setClickListener(this);
        binding.dispensaryList.setAdapter(this.adapter);
    }

    @Override
    public void onConfirmDispenseDrug(int drugId, String employee, String patient, String dosage) {
        dialogService.dismiss(DialogService.Dialog.DISPENSE_DRUG);
        try {
            viewModel.dispenseDrug(drugId, employee, patient, dosage);
        } catch (Exception ex) {
            new AlertDialog.Builder(context())
                    .setTitle(R.string.error_dispense_drug)
                    .setMessage(ex.getMessage())
                    .setNegativeButton(android.R.string.no, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
        Toast.makeText(context(), R.string.dispense, Toast.LENGTH_SHORT).show();
    }
}
