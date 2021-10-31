package ch.ffhs.drugstore.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ch.ffhs.drugstore.databinding.TodoItemBinding;

public class TodoListAdapter extends RecyclerView.Adapter<TodoListAdapter.ViewHolder> {

  private final List<String> todos;
  private ItemClickListener clickListener;
  private ItemLongClickListener longClickListener;

  /**
   * Initialize the dataset of the Adapter.
   *
   * @param data List<String> containing the data to populate views to be used by RecyclerView.
   */
  public TodoListAdapter(List<String> data) {
    todos = data;
  }

  // Create new views (invoked by the layout manager)
  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    TodoItemBinding binding =
        TodoItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
    return new ViewHolder(binding);
  }

  // Replace the contents of a view (invoked by the layout manager)
  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    String todo = todos.get(position);
    holder.textView.setText(todo);
  }

  // total number of rows
  @Override
  public int getItemCount() {
    return todos.size();
  }

  // convenience method for getting data at click position
  public String getItem(int id) {
    return todos.get(id);
  }

  // allows clicks events to be caught
  public void setClickListener(ItemClickListener itemClickListener) {
    this.clickListener = itemClickListener;
  }

  public void setLongClickListener(ItemLongClickListener itemLongClickListener) {
    this.longClickListener = itemLongClickListener;
  }

  // parent activity will implement this method to respond to click events
  public interface ItemClickListener {
    void onItemClick(View view, int position);
  }

  public interface ItemLongClickListener {
    void onItemLongClick(View view, int position);
  }

  /** Provide a reference to the type of views that you are using (custom ViewHolder). */
  public class ViewHolder extends RecyclerView.ViewHolder
      implements View.OnClickListener, View.OnLongClickListener {
    private final TextView textView;

    ViewHolder(TodoItemBinding binding) {
      super(binding.getRoot());
      textView = binding.todoId;
      itemView.setOnClickListener(this);
      itemView.setOnLongClickListener(this);
    }

    @Override
    public void onClick(View view) {
      if (clickListener != null) clickListener.onItemClick(view, getAdapterPosition());
    }

    @Override
    public boolean onLongClick(View view) {
      if (longClickListener != null) longClickListener.onItemLongClick(view, getAdapterPosition());
      return true;
    }
  }
}
