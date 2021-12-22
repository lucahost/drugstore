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
import ch.ffhs.drugstore.databinding.FilterChipBinding;
import ch.ffhs.drugstore.databinding.FragmentDispensaryBinding;
import ch.ffhs.drugstore.presentation.dialog.DialogService;
import ch.ffhs.drugstore.presentation.dialog.DialogType;
import ch.ffhs.drugstore.presentation.dispensary.view.adapter.DispensaryListAdapter;
import ch.ffhs.drugstore.presentation.dispensary.view.adapter.OnDispensaryItemClickListener;
import ch.ffhs.drugstore.presentation.dispensary.view.dialog.ConfirmDispenseDrugListener;
import ch.ffhs.drugstore.presentation.dispensary.view.dialog.DispenseDrugDialogFragmentArgs;
import ch.ffhs.drugstore.presentation.dispensary.viewmodel.DispensaryViewModel;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugDto;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugTypeDto;
import ch.ffhs.drugstore.shared.exceptions.DrugstoreException;
import dagger.hilt.android.AndroidEntryPoint;

/**
 * Dispensary Fragment
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
@AndroidEntryPoint
public class DispensaryFragment extends Fragment
        implements OnDispensaryItemClickListener,
        ConfirmDispenseDrugListener,
        SearchView.OnQueryTextListener {

    @Inject
    protected DispensaryListAdapter adapter;
    @Inject
    protected DialogService dialogService;

    private FragmentDispensaryBinding binding = null;
    private DispensaryViewModel viewModel = null;

    /**
     * {@inheritDoc}
     */
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDispensaryBinding.inflate(getLayoutInflater());
        setupRecyclerView();
        return binding.getRoot();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(DispensaryViewModel.class);
        viewModel.getDispensaryItems().observe(getViewLifecycleOwner(), this::observeItems);
        viewModel.getDrugTypes().observe(getViewLifecycleOwner(), this::setupFilterChips);
        setupSearchBar();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean onQueryTextSubmit(String query) {
        return search(query);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean onQueryTextChange(String newText) {
        return search(newText);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onItemClick(DrugDto drug) {
        DispenseDrugDialogFragmentArgs args =
                new DispenseDrugDialogFragmentArgs(
                        drug.getDrugId(), drug.getTitle(), drug.getDosage(), drug.getUnit());
        dialogService.showDispenseDrugDialog(getChildFragmentManager(), args);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onItemLongClick(DrugDto drug) {
        try {
            viewModel.toggleDrugIsFavorite(drug.getDrugId());
        } catch (DrugstoreException ex) {
            new AlertDialog.Builder(getContext())
                    .setTitle(R.string.error_toggle_favorite)
                    .setMessage(getString(ex.getCode()))
                    .setNegativeButton(R.string.close, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
            return;
        }
        Toast.makeText(
                context(),
                drug.isFavorite() ? R.string.removed_from_favorites : R.string.added_to_favorites,
                Toast.LENGTH_SHORT)
                .show();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onConfirmDispenseDrug(int drugId, String employee, String patient, String amount) {
        dialogService.dismiss(DialogType.DISPENSE_DRUG);
        try {
            viewModel.dispenseDrug(drugId, employee, patient, amount);
        } catch (DrugstoreException ex) {
            new AlertDialog.Builder(getContext())
                    .setTitle(R.string.error_dispense_drug)
                    .setMessage(getString(ex.getCode()))
                    .setNegativeButton(R.string.close, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
            return;
        }
        Toast.makeText(context(), R.string.dispense, Toast.LENGTH_SHORT).show();
    }

    /**
     * Observation of drug dtos. Submits list to adapter and handles empty state.
     *
     * @param drugDtos the drug dtos to be observed
     */
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

    /**
     * Convenience method to access the app context
     *
     * @return app context
     */
    private Context context() {
        return Objects.requireNonNull(this.getActivity()).getApplicationContext();
    }

    /**
     * Setup the search bar
     */
    private void setupSearchBar() {
        binding.searchView.setOnQueryTextListener(this);
        binding.searchView.onActionViewExpanded();
        binding.searchView.clearFocus();
        ImageView clearButton =
                binding.searchView.findViewById(androidx.appcompat.R.id.search_close_btn);
        clearButton.setOnClickListener(
                v -> {
                    binding.searchView.setQuery("", false);
                    binding.searchView.clearFocus();
                });
    }

    /**
     * Setup the filter chips
     *
     * @param drugTypeDtos the drug types to create filter chips
     */
    private void setupFilterChips(@NonNull List<DrugTypeDto> drugTypeDtos) {
        // Get initial filter state
        FilterState<Integer> filterState =
                Objects.requireNonNull(viewModel.getFilterState().getValue());
        // Setup favorite filter
        binding.filterFavorite.setChecked(filterState.isFavorites());
        binding.filterFavorite.setOnCheckedChangeListener(this::onFavoriteChipFilterClick);
        // Setup dynamic drug type filters
        for (DrugTypeDto drugTypeDto : drugTypeDtos) {
            if (binding.chipGroup.findViewById(drugTypeDto.getDrugTypeId()) == null) {
                boolean checked = filterState.getFilters().contains(drugTypeDto.getDrugTypeId());
                FilterChipBinding filterChipBinding = FilterChipBinding.inflate(
                        getLayoutInflater());
                filterChipBinding.filterChip.setId(drugTypeDto.getDrugTypeId());
                filterChipBinding.filterChip.setText(drugTypeDto.getTitle());
                filterChipBinding.filterChip.setChecked(checked);
                filterChipBinding.filterChip.setOnCheckedChangeListener(
                        this::onDrugTypeChipFilterClick);
                binding.chipGroup.addView(filterChipBinding.filterChip);
            }
        }
    }

    /**
     * Search dispensary items by search term
     *
     * @param searchTerm the search term to search for
     * @return search has been handled
     */
    private boolean search(String searchTerm) {
        FilterState<Integer> currentFilters = viewModel.getFilterState().getValue();
        assert currentFilters != null;
        currentFilters.setSearchFilter(searchTerm);
        viewModel.filter(currentFilters);
        return true;
    }

    /**
     * Filter dispensary items by favorites
     *
     * @param buttonView the clicked filter button
     * @param isChecked  if the filter has been checked
     */
    private void onFavoriteChipFilterClick(CompoundButton buttonView, boolean isChecked) {
        FilterState<Integer> currentFilters = viewModel.getFilterState().getValue();
        assert currentFilters != null;
        currentFilters.toggleFavorites();
        viewModel.filter(currentFilters);
    }

    /**
     * Filter dispensary items by filter chips
     *
     * @param buttonView the clicked filter button
     * @param isChecked  if the filter has been checked
     */
    private void onDrugTypeChipFilterClick(CompoundButton buttonView, boolean isChecked) {
        FilterState<Integer> currentFilters = viewModel.getFilterState().getValue();
        assert currentFilters != null;
        currentFilters.toggleFilter(buttonView.getId());
        viewModel.filter(currentFilters);
    }

    /**
     * Setup the recycler view list
     */
    private void setupRecyclerView() {
        binding.dispensaryList.setLayoutManager(
                new GridLayoutManager(context(), 2, RecyclerView.VERTICAL, false));
        adapter.setClickListener(this);
        binding.dispensaryList.setAdapter(this.adapter);
    }
}
