package ch.ffhs.drugstore.presentation.management.inventory.view.adapter;

import android.content.Context;
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

import ch.ffhs.drugstore.R;
import ch.ffhs.drugstore.databinding.InventoryItemBinding;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugDto;

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
    private Context context;

    @Inject
    public InventoryListAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public InventoryItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        InventoryItemBinding binding =
                InventoryItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent,
                        false);
        context = binding.getRoot().getContext();
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

    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder).
     */
    public class InventoryItemHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener {
        private final CheckedTextView title;
        private final TextView secondary;
        // private final TextView deviation;

        InventoryItemHolder(InventoryItemBinding binding) {
            super(binding.getRoot());
            title = binding.title;
            secondary = binding.secondary;
            // deviation = binding.deviation;
        }

        void bind(int position) {
            title.setOnClickListener(this);
            title.setText(getItemTitleText(position));
            secondary.setText(getItemSecondaryText(position));
        }

        @NonNull
        private String getItemTitleText(int position) {
            return context.getString(R.string.drug_title_and_dosage,
                    getItem(position).getTitle(),
                    getItem(position).getDosage());
        }

        @NonNull
        private String getItemSecondaryText(int position) {
            return context.getString(R.string.drug_stock_unit,
                    getItem(position).getStockAmount(),
                    getItem(position).getUnit());
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) {
                title.setChecked(!title.isChecked());
                clickListener.onItemClick(getItem(getAdapterPosition()));
            }
        }
    }
}
