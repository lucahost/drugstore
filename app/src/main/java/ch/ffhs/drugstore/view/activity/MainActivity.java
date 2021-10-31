package ch.ffhs.drugstore.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;

import ch.ffhs.drugstore.databinding.ActivityMainBinding;
import ch.ffhs.drugstore.view.Todo;
import ch.ffhs.drugstore.view.adapter.TodoListAdapter;
import ch.ffhs.drugstore.view.fragment.AddTodoDialogFragment;

public class MainActivity extends AppCompatActivity
    implements TodoListAdapter.ItemClickListener,
        TodoListAdapter.ItemLongClickListener,
        AddTodoDialogFragment.ConfirmAddTodoListener {

  TodoListAdapter adapter;
  ArrayList<Todo> todos;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());

    // data to populate the RecyclerView with
    todos = new ArrayList<>();
    todos.add(new Todo("Do this", false));
    todos.add(new Todo("And that", false));
    todos.add(new Todo("Never forget this", false));
    todos.add(new Todo("Maybe also this", false));

    binding.fab.setOnClickListener(
        view ->
            new AddTodoDialogFragment()
                .show(getSupportFragmentManager(), AddTodoDialogFragment.TAG));

    // set up the RecyclerView
    binding.todos.setLayoutManager(new LinearLayoutManager(this));
    adapter = new TodoListAdapter(todos);
    adapter.setClickListener(this);
    adapter.setLongClickListener(this);
    binding.todos.setAdapter(adapter);
  }

  @Override
  public void onItemClick(View view, int position) {
    Todo todo = adapter.getItem(position);
    todo.toggle();
    adapter.notifyItemChanged(position);
    String checkMessage = todo.isChecked() ? "Checked " : "Unchecked ";
    Toast.makeText(
            this,
            String.format("%s \"%s\"", checkMessage, adapter.getItem(position).getText()),
            Toast.LENGTH_SHORT)
        .show();
  }

  @Override
  public void onItemLongClick(View view, int position) {
    Toast.makeText(
            this,
            String.format("Removed %s", adapter.getItem(position).getText()),
            Toast.LENGTH_SHORT)
        .show();
    todos.remove(position);
    adapter.notifyItemRemoved(position);
  }

  @Override
  public void onConfirmAddTodo(String text) {
    Toast.makeText(this, "Added " + text, Toast.LENGTH_SHORT).show();
    todos.add(new Todo(text, false));
  }
}
