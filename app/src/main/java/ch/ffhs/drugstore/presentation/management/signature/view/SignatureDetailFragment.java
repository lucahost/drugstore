package ch.ffhs.drugstore.presentation.management.signature.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.Objects;

import javax.inject.Inject;

import ch.ffhs.drugstore.R;
import ch.ffhs.drugstore.databinding.FragmentSignatureDetailBinding;
import ch.ffhs.drugstore.presentation.management.signature.view.adapter.SignatureDetailListAdapter;
import ch.ffhs.drugstore.presentation.management.signature.viewmodel.SignatureViewModel;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SignatureDetailFragment extends Fragment {

    @Inject
    SignatureDetailListAdapter adapter;
    FragmentSignatureDetailBinding binding;
    SignatureViewModel viewModel;

    @Inject
    public SignatureDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        binding = FragmentSignatureDetailBinding.inflate(getLayoutInflater());
        setupRecyclerView();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(SignatureViewModel.class);
        int signatureId = SignatureDetailFragmentArgs.fromBundle(getArguments()).getSignatureId();
        viewModel.getSignatureDrugBySignatureId(signatureId).observe(getViewLifecycleOwner(), adapter::submitList);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        super.onPrepareOptionsMenu(menu);
        MenuItem backItem = menu.findItem(R.id.toolbar_back);
        backItem.setVisible(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.toolbar_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.toolbar_back) {
            NavDirections action =
                    SignatureDetailFragmentDirections.actionSignatureDetailFragmentToSignature();
            Navigation.findNavController(binding.getRoot()).navigate(action);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private Context context() {
        return Objects.requireNonNull(this.getActivity()).getApplicationContext();
    }

    private void setupRecyclerView() {
        binding.signatureDetailList.setLayoutManager(new LinearLayoutManager(context()));
        binding.signatureDetailList.addItemDecoration(new DividerItemDecoration(context(), DividerItemDecoration.VERTICAL));
        binding.signatureDetailList.setAdapter(this.adapter);
    }
}
