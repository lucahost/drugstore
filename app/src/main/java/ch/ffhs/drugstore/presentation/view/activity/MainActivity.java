package ch.ffhs.drugstore.presentation.view.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import ch.ffhs.drugstore.databinding.ActivityMainBinding;
import ch.ffhs.drugstore.presentation.view.fragment.TodoListFragment;
import ch.ffhs.drugstore.presentation.viewmodel.TodoListViewModel;

public class MainActivity extends AppCompatActivity {

  TodoListViewModel viewModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());

    // This should be done by DI
    TodoListFragment todoListFragment = new TodoListFragment();

    viewModel = new ViewModelProvider(this).get(TodoListViewModel.class);

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
