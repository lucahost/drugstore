package ch.ffhs.drugstore.presentation.management.inventory.view.adapter;

import android.content.Context;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ch.ffhs.drugstore.R;
import ch.ffhs.drugstore.databinding.InventoryItemBinding;

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
        title.setChecked(binding.title.isChecked());
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
        title.setChecked(getIsSelected(position));
    }

    /**
     * @param position The state if the item is selected
     * @return isSelected boolean
     */
    private boolean getIsSelected(int position) {
        return inventoryListAdapter.getItem(position).isSelected();
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
                    inventoryListAdapter.getItem(getAdapterPosition()),
                    title.isChecked());
        }
    }
}