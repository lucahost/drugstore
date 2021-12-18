package ch.ffhs.drugstore.presentation.management.inventory.view.adapter;

import static ch.ffhs.drugstore.presentation.management.ListItemItemDiffHelper.drugDtoItemDiffCallback;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;

import javax.inject.Inject;

import ch.ffhs.drugstore.databinding.InventoryItemBinding;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugDto;

/**
 * Recycler view list adapter for {@link DrugDto} items used by {@link InventoryItemHolder} view
 * holder.
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class InventoryListAdapter
        extends ListAdapter<DrugDto, InventoryItemHolder> {
    private OnHistoryItemClickListener clickListener;

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
        return new InventoryItemHolder(this, binding);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onBindViewHolder(@NonNull InventoryItemHolder holder, int position) {
        holder.bind(position);
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
}
