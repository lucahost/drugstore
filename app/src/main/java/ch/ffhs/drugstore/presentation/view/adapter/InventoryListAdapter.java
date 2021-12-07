package ch.ffhs.drugstore.presentation.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import javax.inject.Inject;

import ch.ffhs.drugstore.data.entity.InventoryDrug;
import ch.ffhs.drugstore.databinding.InventoryItemBinding;

public class InventoryListAdapter
    extends ListAdapter<InventoryDrug, InventoryListAdapter.InventoryItemHolder> {
  private static final DiffUtil.ItemCallback<InventoryDrug> DIFF_CALLBACK =
      new DiffUtil.ItemCallback<InventoryDrug>() {
        @Override
        public boolean areItemsTheSame(
            @NonNull InventoryDrug oldItem, @NonNull InventoryDrug newItem) {
          return oldItem.getInventoryDrugId() == newItem.getInventoryDrugId();
        }

        @Override
        public boolean areContentsTheSame(
            @NonNull InventoryDrug oldItem, @NonNull InventoryDrug newItem) {
          return oldItem.getDrugId() == newItem.getDrugId()
              && oldItem.getAmount() == newItem.getAmount();
        }
      };

  private InventoryListAdapter.OnItemClickListener clickListener;

  @Inject
  public InventoryListAdapter() {
    super(DIFF_CALLBACK);
  }

  @NonNull
  @Override
  public InventoryItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    InventoryItemBinding binding =
        InventoryItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
    return new InventoryItemHolder(binding);
  }

  @Override
  public void onBindViewHolder(@NonNull InventoryItemHolder holder, int position) {
    holder.bind(position);
  }

  // allows clicks events to be caught
  public void setClickListener(OnItemClickListener itemClickListener) {
    this.clickListener = itemClickListener;
  }

  public interface OnItemClickListener {
    void onItemClick(InventoryDrug inventoryDrug);

    void onItemLongClick(InventoryDrug inventoryDrug);
  }

  /** Provide a reference to the type of views that you are using (custom ViewHolder). */
  public class InventoryItemHolder extends RecyclerView.ViewHolder
      implements View.OnClickListener, View.OnLongClickListener {
    private final CheckedTextView title;
    private final TextView secondary;

    InventoryItemHolder(InventoryItemBinding binding) {
      super(binding.getRoot());
      title = binding.title;
      secondary = binding.secondary;
    }

    void bind(int position) {
      title.setChecked(getItem(position).isApproved());
      title.setText(String.format("Drug Id: %d", getItem(position).getDrugId()));
      secondary.setText(String.format("Amount: %d", getItem(position).getAmount()));
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
