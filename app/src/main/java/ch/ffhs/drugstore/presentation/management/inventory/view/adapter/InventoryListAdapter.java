package ch.ffhs.drugstore.presentation.management.inventory.view.adapter;

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

import ch.ffhs.drugstore.shared.dto.management.drugs.DrugDto;
import ch.ffhs.drugstore.databinding.InventoryItemBinding;

public class InventoryListAdapter
    extends ListAdapter<DrugDto, InventoryListAdapter.InventoryItemHolder> {
  private static final DiffUtil.ItemCallback<DrugDto> DIFF_CALLBACK =
      new DiffUtil.ItemCallback<DrugDto>() {
        @Override
        public boolean areItemsTheSame(
            @NonNull DrugDto oldItem, @NonNull DrugDto newItem) {
          return oldItem.getDrugId() == newItem.getDrugId();
        }

        @Override
        public boolean areContentsTheSame(
            @NonNull DrugDto oldItem, @NonNull DrugDto newItem) {
          return oldItem.getDrugId() == newItem.getDrugId()
              && oldItem.getStockAmount() == newItem.getStockAmount();
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
    void onItemClick(DrugDto drugDto);
  }

  /** Provide a reference to the type of views that you are using (custom ViewHolder). */
  public class InventoryItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final CheckedTextView title;
    private final TextView secondary;

    InventoryItemHolder(InventoryItemBinding binding) {
      super(binding.getRoot());
      title = binding.title;
      secondary = binding.secondary;
    }

    void bind(int position) {
      title.setOnClickListener(this);
      title.setText(String.format("Drug Id: %d", getItem(position).getDrugId()));
      secondary.setText(String.format("Amount: %.2f", getItem(position).getStockAmount()));
    }

    @Override
    public void onClick(View view) {
      if (clickListener != null) clickListener.onItemClick(getItem(getAdapterPosition()));
    }
  }
}
