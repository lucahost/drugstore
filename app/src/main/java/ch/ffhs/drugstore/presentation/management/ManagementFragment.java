package ch.ffhs.drugstore.presentation.management;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
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
  NavigationView managementNavigation;

  @Inject
  public ManagementFragment() {
    // Required empty public constructor
  }

  @Override
  public View onCreateView(
      @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    binding = FragmentManagementBinding.inflate(inflater, container, false);
    managementNavigation = binding.managementNavigation;
    setUpNavigation();
    return binding.getRoot();
  }

  public void setUpNavigation() {
    NavHostFragment navHostFragment =
            (NavHostFragment)
                    getChildFragmentManager().findFragmentById(R.id.management_nav_host_fragment);
    assert navHostFragment != null;
    NavController navController = navHostFragment.getNavController();
    NavigationUI.setupWithNavController(managementNavigation, navController);
  }
}
