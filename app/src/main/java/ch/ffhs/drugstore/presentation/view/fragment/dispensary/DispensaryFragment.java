package ch.ffhs.drugstore.presentation.view.fragment.dispensary;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Objects;

import javax.inject.Inject;

import ch.ffhs.drugstore.data.entity.Drug;
import ch.ffhs.drugstore.databinding.FragmentDispensaryBinding;
import ch.ffhs.drugstore.presentation.view.adapter.DispensaryListAdapter;
import ch.ffhs.drugstore.presentation.viewmodel.DispensaryViewModel;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DispensaryFragment extends Fragment
    implements DispensaryListAdapter.OnItemClickListener,
        DispenseDrugDialogFragment.ConfirmDispenseDrugListener {

  @Inject DispensaryListAdapter adapter;
  @Inject DispenseDrugDialogFragment dispenseDrugDialogFragment;
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
  }

  public Context context() {
    return Objects.requireNonNull(this.getActivity()).getApplicationContext();
  }

  @Override
  public void onItemClick(Drug drug) {
    dispenseDrugDialogFragment.show(getChildFragmentManager(), DispenseDrugDialogFragment.TAG);
  }

  @Override
  public void onItemLongClick(Drug drug) {
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
    dispenseDrugDialogFragment.dismiss();
    viewModel.dispenseDrug();
    Toast.makeText(context(), "Ausgegeben", Toast.LENGTH_SHORT).show();
  }
}
