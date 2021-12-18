package ch.ffhs.drugstore.presentation;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import ch.ffhs.drugstore.R;
import ch.ffhs.drugstore.databinding.ActivityMainBinding;
import dagger.hilt.android.AndroidEntryPoint;

/**
 * The single main activity of the app.
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

  private BottomNavigationView bottomNavigationView;

  /**
   * {@inheritDoc}
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    SplashScreen.installSplashScreen(this);
    if (isTablet()) {
      ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
      setContentView(binding.getRoot());
      bottomNavigationView = binding.bottomNavigation;
      setUpNavigation();
    } else {
      setContentView(R.layout.device_not_supported);
    }
  }

  /**
   * Setup the botton tab navigation.
   */
  private void setUpNavigation() {
    NavHostFragment navHostFragment =
        (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
    assert navHostFragment != null;
    NavController navController = navHostFragment.getNavController();
    NavigationUI.setupWithNavController(bottomNavigationView, navController);
  }

  /**
   * Checks if the app is running on a tablet (large) device.
   * @return if the app is running on a large device.
   */
  private boolean isTablet() {
    return Boolean.parseBoolean(
            getApplicationContext().getResources().getString(R.string.is_supported_screen));
  }
}
