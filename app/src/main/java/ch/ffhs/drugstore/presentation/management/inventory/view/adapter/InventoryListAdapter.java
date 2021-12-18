package ch.ffhs.drugstore.presentation.management.inventory.view.adapter;

import android.annotation.SuppressLint;
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

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.R;
import ch.ffhs.drugstore.databinding.InventoryItemBinding;
import ch.ffhs.drugstore.shared.dto.management.drugs.SelectableDrugDto;

public class InventoryListAdapter
        extends ListAdapter<SelectableDrugDto, InventoryListAdapter.InventoryItemHolder> {
    private static final DiffUtil.ItemCallback<SelectableDrugDto> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<SelectableDrugDto>() {
                @Override
                public boolean areItemsTheSame(
                        @NonNull SelectableDrugDto oldItem, @NonNull SelectableDrugDto newItem) {
                    return oldItem.getDrugId() == newItem.getDrugId();
                }

                @Override
                public boolean areContentsTheSame(
                        @NonNull SelectableDrugDto oldItem, @NonNull SelectableDrugDto newItem) {
                    return oldItem.getDrugId() == newItem.getDrugId()
                            && oldItem.getStockAmount() == newItem.getStockAmount();
                }
            };
    private static final List<InventoryItemHolder> itemHolders = new ArrayList<>();
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
        itemHolders.add(holder);
    }

    // allows clicks events to be caught
    public void setClickListener(OnItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void toggleCheckAll(boolean checkState) {
        List<SelectableDrugDto> drugDtoList = getCurrentList();
        for (InventoryItemHolder itemHolder : itemHolders) {
            itemHolder.title.setChecked(checkState);
            int itemPos = itemHolder.getLayoutPosition();
            SelectableDrugDto drug = drugDtoList.get(itemPos);
            drug.setSelected(checkState);
            this.clickListener.onItemClick(drug);
        }
        notifyDataSetChanged();
    }


    public interface OnItemClickListener {
        void onItemClick(SelectableDrugDto drugDto);
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
            title.setChecked(binding.title.isChecked());
            // deviation = binding.deviation;
        }

        void bind(int position) {
            title.setOnClickListener(this);
            title.setText(getItemTitleText(position));
            secondary.setText(getItemSecondaryText(position));
            title.setChecked(getIsSelected(position));
        }

        @NonNull
        private boolean getIsSelected(int position) {
            return getItem(position).isSelected();
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
