package ch.ffhs.drugstore.presentation.management.signature.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
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

import ch.ffhs.drugstore.data.relation.SignatureWithUserAndSignatureDrugsAndDrugs;
import ch.ffhs.drugstore.databinding.FragmentSignatureBinding;
import ch.ffhs.drugstore.presentation.management.signature.view.adapter.SignatureListAdapter;
import ch.ffhs.drugstore.presentation.management.signature.viewmodel.SignatureViewModel;
import ch.ffhs.drugstore.shared.dto.management.signature.SignatureDto;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SignatureFragment extends Fragment
        implements SignatureListAdapter.OnItemClickListener {

    @Inject
    SignatureListAdapter adapter;
    FragmentSignatureBinding binding;
    SignatureViewModel viewModel;

    @Inject
    public SignatureFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSignatureBinding.inflate(getLayoutInflater());
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
        viewModel = new ViewModelProvider(requireActivity()).get(SignatureViewModel.class);
        viewModel.getItems().observe(getViewLifecycleOwner(), adapter::submitList);
    }

    public Context context() {
        return Objects.requireNonNull(this.getActivity()).getApplicationContext();
    }

    @Override
    public void onItemClick(View view, SignatureDto signature) {
        NavDirections action = SignatureFragmentDirections.actionSignatureToSignatureDetailFragment(
                (int) signature.getSignatureId());
        Navigation.findNavController(view).navigate(action);
    }

    private void setupRecyclerView() {
        binding.signatureList.setLayoutManager(new LinearLayoutManager(context()));
        binding.signatureList.addItemDecoration(new DividerItemDecoration(context(), DividerItemDecoration.VERTICAL));
        adapter.setClickListener(this);
        binding.signatureList.setAdapter(this.adapter);
    }
}
