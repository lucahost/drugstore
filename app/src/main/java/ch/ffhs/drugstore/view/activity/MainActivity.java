package ch.ffhs.drugstore.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;

import ch.ffhs.drugstore.databinding.ActivityMainBinding;
import ch.ffhs.drugstore.view.adapter.TodoListAdapter;
import ch.ffhs.drugstore.view.fragment.AddTodoDialogFragment;

public class MainActivity extends AppCompatActivity
    implements TodoListAdapter.ItemClickListener,
        TodoListAdapter.ItemLongClickListener,
        AddTodoDialogFragment.ConfirmAddTodoListener {

  TodoListAdapter adapter;
  ArrayList<String> todos;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());

    // data to populate the RecyclerView with
    todos = new ArrayList<>();
    todos.add("Do this");
    todos.add("And that");
    todos.add("Never forget this");
    todos.add("Maybe also this");

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
    Toast.makeText(
            this,
            "You clicked " + adapter.getItem(position) + " on row number " + position,
            Toast.LENGTH_SHORT)
        .show();
  }

  @Override
  public void onItemLongClick(View view, int position) {
    Toast.makeText(
            this,
            "Removed " + adapter.getItem(position) + " on row number " + position,
            Toast.LENGTH_SHORT)
        .show();
    todos.remove(position);
    adapter.notifyItemRemoved(position);
  }

  @Override
  public void onConfirmAddTodo(String text) {
    Toast.makeText(this, "Added " + text, Toast.LENGTH_SHORT).show();
    todos.add(text);
  }
}
