package ch.ffhs.drugstore.presentation.view.fragment.management;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import javax.inject.Inject;

import ch.ffhs.drugstore.R;
import ch.ffhs.drugstore.databinding.FragmentManagementBinding;

public class ManagementFragment extends Fragment {

  FragmentManagementBinding binding;

  @Inject
  public ManagementFragment() {
    // Required empty public constructor
  }

  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    binding = FragmentManagementBinding.inflate(inflater, container, false);
    NavigationView managementNavigation = binding.managementNavigation;
    NavHostFragment navHostFragment =
        (NavHostFragment)
            getChildFragmentManager().findFragmentById(R.id.management_nav_host_fragment);
    assert navHostFragment != null;
    NavController navController = navHostFragment.getNavController();
    NavigationUI.setupWithNavController(managementNavigation, navController);
    return binding.getRoot();
  }
}
