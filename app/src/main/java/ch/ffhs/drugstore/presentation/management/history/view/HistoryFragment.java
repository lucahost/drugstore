package ch.ffhs.drugstore.presentation.management.history.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.Objects;

import javax.inject.Inject;

import ch.ffhs.drugstore.databinding.FragmentHistoryBinding;
import ch.ffhs.drugstore.presentation.management.history.view.adapter.HistoryListAdapter;
import ch.ffhs.drugstore.presentation.management.history.viewmodel.HistoryViewModel;
import dagger.hilt.android.AndroidEntryPoint;

/**
 * History Fragment
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
@AndroidEntryPoint
public class HistoryFragment extends Fragment {

    @Inject
    protected HistoryListAdapter adapter;
    private FragmentHistoryBinding binding;

    /**
     * {@inheritDoc}
     */
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHistoryBinding.inflate(getLayoutInflater());
        setupRecyclerView();
        return binding.getRoot();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        HistoryViewModel viewModel =
                new ViewModelProvider(requireActivity()).get(HistoryViewModel.class);
        viewModel.getTransactions().observe(getViewLifecycleOwner(), adapter::submitList);
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
     * Setup the recycler view list
     */
    private void setupRecyclerView() {
        binding.historyList.setLayoutManager(new LinearLayoutManager(context()));
        binding.historyList.addItemDecoration(
                new DividerItemDecoration(context(), DividerItemDecoration.VERTICAL));
        binding.historyList.setAdapter(this.adapter);
    }
}
