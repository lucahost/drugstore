package ch.ffhs.drugstore.presentation.management.inventory.view.adapter;

import static ch.ffhs.drugstore.presentation.management.ListItemItemDiffHelper.drugDtoItemDiffCallback;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.R;
import ch.ffhs.drugstore.databinding.InventoryItemBinding;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugDto;

/**
 * Recycler view list adapter for {@link DrugDto} items used by {@link InventoryItemHolder} view
 * holder.
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class InventoryListAdapter extends ListAdapter<DrugDto, InventoryListAdapter.InventoryItemHolder> {
    private static final List<InventoryItemHolder> itemHolders = new ArrayList<>();
    private OnHistoryItemClickListener clickListener;
    private Context context;

    /**
     * Constructs a {@link InventoryListAdapter}
     */
    @Inject
    public InventoryListAdapter() {
        super(drugDtoItemDiffCallback);
    }

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    public InventoryItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        InventoryItemBinding binding =
                InventoryItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent,
                        false);
        context = binding.getRoot().getContext();
        return new InventoryItemHolder(this, binding);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onBindViewHolder(@NonNull InventoryItemHolder holder, int position) {
        holder.bind(position);
        itemHolders.add(holder);
    }

    /**
     * @return click listener
     */
    protected OnHistoryItemClickListener getClickListener() {
        return clickListener;
    }

    /**
     * allows clicks events to be caught
     *
     * @param itemClickListener click listener
     */
    public void setClickListener(OnHistoryItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    public void toggleCheckAll(boolean checkState) {
        List<DrugDto> drugDtoList = getCurrentList();
        for (InventoryItemHolder itemHolder : itemHolders) {
            itemHolder.title.setChecked(checkState);
            int itemPos = itemHolder.getLayoutPosition();
            DrugDto drug = drugDtoList.get(itemPos);
            this.clickListener.onItemClick(drug);
        }
    }

    /**
     *
     */
    @SuppressLint("NotifyDataSetChanged")
    public void resetCheckState() {
        notifyDataSetChanged();
    }

    /**
     * @param position The position of the item within the adapter's data set
     * @return The item at specified position
     */
    protected DrugDto getItem(int position) {
        return super.getItem(position);
    }

    /**
     * Recycler view item holder for {@link InventoryListAdapter} items
     *
     * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
     * @version 2021.12.15
     */
    public class InventoryItemHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener {
        private final InventoryListAdapter inventoryListAdapter;
        private final CheckedTextView title;
        private final TextView secondary;
        // private final TextView deviation;
        private final Context context;

        /**
         * Constructs a {@link InventoryItemHolder}
         *
         * @param inventoryListAdapter the list adapter
         * @param binding              the view binding
         */
        InventoryItemHolder(InventoryListAdapter inventoryListAdapter,
                InventoryItemBinding binding) {
            super(binding.getRoot());
            this.context = binding.getRoot().getContext();
            this.inventoryListAdapter = inventoryListAdapter;
            title = binding.title;
            secondary = binding.secondary;
            // deviation = binding.deviation;
        }

        /**
         * Set the initial values and listeners on the view binding.
         *
         * @param position The position of the item within the adapter's data set.
         */
        protected void bind(int position) {
            title.setOnClickListener(this);
            title.setText(getItemTitleText(position));
            secondary.setText(getItemSecondaryText(position));
        }

        /**
         * @param position The position of the item within the adapter's data set.
         * @return title text
         */
        @NonNull
        private String getItemTitleText(int position) {
            return context.getString(R.string.drug_title_and_dosage,
                    inventoryListAdapter.getItem(position).getTitle(),
                    inventoryListAdapter.getItem(position).getDosage());
        }

        /**
         * @param position The position of the item within the adapter's data set.
         * @return secondary text
         */
        @NonNull
        private String getItemSecondaryText(int position) {
            return context.getString(R.string.drug_stock_unit,
                    inventoryListAdapter.getItem(position).getStockAmount(),
                    inventoryListAdapter.getItem(position).getUnit());
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void onClick(View view) {
            if (inventoryListAdapter.getClickListener() != null) {
                title.setChecked(!title.isChecked());
                inventoryListAdapter.getClickListener().onItemClick(
                        inventoryListAdapter.getItem(getAdapterPosition()));
            }
        }
    }
}
