package ch.ffhs.drugstore.presentation.management.history.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import javax.inject.Inject;

import ch.ffhs.drugstore.data.entity.Transaction;
import ch.ffhs.drugstore.databinding.HistoryItemBinding;

public class HistoryListAdapter
    extends ListAdapter<Transaction, HistoryListAdapter.HistoryItemHolder> {
  private static final DiffUtil.ItemCallback<Transaction> DIFF_CALLBACK =
      new DiffUtil.ItemCallback<Transaction>() {
        @Override
        public boolean areItemsTheSame(@NonNull Transaction oldItem, @NonNull Transaction newItem) {
          return oldItem.getTransactionId() == newItem.getTransactionId();
        }

        @Override
        public boolean areContentsTheSame(
            @NonNull Transaction oldItem, @NonNull Transaction newItem) {
          return oldItem.getDrugId() == newItem.getDrugId()
              && oldItem.getAmount() == newItem.getAmount();
        }
      };

  private HistoryListAdapter.OnItemClickListener clickListener;

  @Inject
  public HistoryListAdapter() {
    super(DIFF_CALLBACK);
  }

  @NonNull
  @Override
  public HistoryItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    HistoryItemBinding binding =
        HistoryItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
    return new HistoryItemHolder(binding);
  }

  @Override
  public void onBindViewHolder(@NonNull HistoryItemHolder holder, int position) {
    holder.bind(position);
  }

  // allows clicks events to be caught
  public void setClickListener(OnItemClickListener itemClickListener) {
    this.clickListener = itemClickListener;
  }

  public interface OnItemClickListener {
    void onItemClick(Transaction transaction);

    void onItemLongClick(Transaction transaction);
  }

  /** Provide a reference to the type of views that you are using (custom ViewHolder). */
  public class HistoryItemHolder extends RecyclerView.ViewHolder
      implements View.OnClickListener, View.OnLongClickListener {
    private final TextView title;
    private final TextView secondary;

    HistoryItemHolder(HistoryItemBinding binding) {
      super(binding.getRoot());
      title = binding.title;
      secondary = binding.secondary;
    }

    void bind(int position) {
      title.setText(String.format("Transaction Id: %d", getItem(position).getTransactionId()));
      secondary.setText(
          String.format("Created at: %s", getItem(position).getCreatedAt().toString()));
    }

    @Override
    public void onClick(View view) {
      if (clickListener != null) clickListener.onItemClick(getItem(getAdapterPosition()));
    }

    @Override
    public boolean onLongClick(View view) {
      if (clickListener != null) clickListener.onItemLongClick(getItem(getAdapterPosition()));
      return true;
    }
  }
}
