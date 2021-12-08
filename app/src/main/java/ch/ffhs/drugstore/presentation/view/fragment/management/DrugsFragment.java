package ch.ffhs.drugstore.presentation.view.fragment.management;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.Objects;

import javax.inject.Inject;

import ch.ffhs.drugstore.R;
import ch.ffhs.drugstore.data.entity.Drug;
import ch.ffhs.drugstore.databinding.FragmentDrugsBinding;
import ch.ffhs.drugstore.presentation.view.adapter.DrugListAdapter;
import ch.ffhs.drugstore.presentation.viewmodel.DrugsViewModel;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DrugsFragment extends Fragment
    implements DrugListAdapter.OnItemClickListener,
        CreateDrugDialogFragment.ConfirmCreateDrugListener,
        AddDrugDialogFragment.ConfirmAddDrugListener,
        RemoveDrugDialogFragment.ConfirmRemoveDrugListener,
        EditDrugDialogFragment.ConfirmEditDrugListener,
        DeleteDrugDialogFragment.ConfirmDeleteDrugListener {

  @Inject DrugListAdapter adapter;
  @Inject CreateDrugDialogFragment createDrugDialogFragment;
  @Inject AddDrugDialogFragment addDrugDialogFragment;
  @Inject RemoveDrugDialogFragment removeDrugDialogFragment;
  @Inject EditDrugDialogFragment editDrugDialogFragment;
  @Inject DeleteDrugDialogFragment deleteDrugDialogFragment;
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
  public void onItemClick(View view, Drug drug) {
    showPopup(view, drug);
  }

  private void showPopup(View v, Drug drug) {
    PopupMenu popup = new PopupMenu(Objects.requireNonNull(getContext()), v);
    popup.setOnMenuItemClickListener(getMenuItemClickListener(drug));
    MenuInflater inflater = popup.getMenuInflater();
    inflater.inflate(R.menu.management_drug_popup_menu, popup.getMenu());
    popup.show();
  }

  @NonNull
  private PopupMenu.OnMenuItemClickListener getMenuItemClickListener(Drug drug) {
    return item -> {
      int itemId = item.getItemId();
      if (itemId == R.id.drug_item_add) {
        addDrugDialogFragment.show(getChildFragmentManager(), AddDrugDialogFragment.TAG);
        return true;
      } else if (itemId == R.id.drug_item_remove) {
        removeDrugDialogFragment.show(getChildFragmentManager(), RemoveDrugDialogFragment.TAG);
        return true;
      } else if (itemId == R.id.drug_item_edit) {
        editDrugDialogFragment.show(getChildFragmentManager(), EditDrugDialogFragment.TAG);
        return true;
      } else if (itemId == R.id.drug_item_delete) {
        deleteDrugDialogFragment.show(getChildFragmentManager(), DeleteDrugDialogFragment.TAG);
        return true;
      }
      return false;
    };
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

  @Override
  public void onConfirmAddDrug(String content, String unit, String count) {
    Toast.makeText(context(), "Erfolgreich hinzugefügt", Toast.LENGTH_SHORT).show();
  }

  @Override
  public void onConfirmDeleteDrug(int drugId) {
    Toast.makeText(context(), "Erfolgreich gelöscht", Toast.LENGTH_SHORT).show();
  }

  @Override
  public void onConfirmEditDrug(
      String name, String dosage, String category, String dispenseUnit, String tolerance) {
    Toast.makeText(context(), "Erfolgreich bearbeitet", Toast.LENGTH_SHORT).show();
  }

  @Override
  public void onConfirmRemoveDrug() {
    Toast.makeText(context(), "Erfolgreich ausgetragen", Toast.LENGTH_SHORT).show();
  }
}
