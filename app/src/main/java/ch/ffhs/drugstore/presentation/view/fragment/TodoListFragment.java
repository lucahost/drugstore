package ch.ffhs.drugstore.presentation.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import javax.inject.Inject;

import ch.ffhs.drugstore.data.entity.Todo;
import ch.ffhs.drugstore.databinding.TodoListBinding;
import ch.ffhs.drugstore.presentation.view.adapter.TodoListAdapter;
import ch.ffhs.drugstore.presentation.viewmodel.TodoListViewModel;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class TodoListFragment extends Fragment
    implements TodoListAdapter.OnTodoClickListener, AddTodoDialogFragment.ConfirmAddTodoListener {

  @Inject TodoListAdapter adapter;
  @Inject AddTodoDialogFragment addTodoDialogFragment;
  TodoListBinding binding;
  TodoListViewModel viewModel;

  @Inject
  public TodoListFragment() {
    // TODO document why this constructor is empty
  }

  @Override
  public View onCreateView(
      @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    binding = TodoListBinding.inflate(getLayoutInflater());
    binding.fab.setOnClickListener(
        view -> addTodoDialogFragment.show(getChildFragmentManager(), AddTodoDialogFragment.TAG));
    setupRecyclerView();
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    viewModel = new ViewModelProvider(requireActivity()).get(TodoListViewModel.class);
    viewModel.getTodos().observe(getViewLifecycleOwner(), adapter::submitList);
  }

  public Context context() {
    return this.getActivity().getApplicationContext();
  }

  private void setupRecyclerView() {
    binding.todos.setLayoutManager(new LinearLayoutManager(context()));
    adapter.setClickListener(this);
    binding.todos.setAdapter(this.adapter);
  }

  @Override
  public void onItemClick(Todo todo) {
    viewModel.checkTodo(todo);
    String checkMessage = todo.isChecked() ? "Checked " : "Unchecked ";
    Toast.makeText(
            context(), String.format("%s \"%s\"", checkMessage, todo.getText()), Toast.LENGTH_SHORT)
        .show();
  }

  @Override
  public void onItemLongClick(Todo todo) {
    viewModel.deleteTodo(todo);
    Toast.makeText(context(), String.format("Removed %s", todo.getText()), Toast.LENGTH_SHORT)
        .show();
  }

  @Override
  public void onConfirmAddTodo(String text) {
    viewModel.addTodo(text);
    Toast.makeText(context(), String.format("Add %s", text), Toast.LENGTH_SHORT).show();
  }
}
