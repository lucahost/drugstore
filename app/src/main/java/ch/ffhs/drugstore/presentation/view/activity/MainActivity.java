package ch.ffhs.drugstore.presentation.view.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import javax.inject.Inject;

import ch.ffhs.drugstore.databinding.ActivityMainBinding;
import ch.ffhs.drugstore.presentation.view.fragment.TodoListFragment;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

  @Inject TodoListFragment todoListFragment;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());

    if (savedInstanceState == null) {
      addFragment(binding.fragmentContainerView.getId(), todoListFragment);
    }
  }

  private void addFragment(int fragmentContainerId, Fragment fragment) {
    getSupportFragmentManager()
        .beginTransaction()
        .setReorderingAllowed(true)
        .add(fragmentContainerId, fragment, null)
        .commit();
  }
}
