package ch.ffhs.drugstore.presentation.management.inventory.view.adapter;

import static ch.ffhs.drugstore.presentation.helpers.ListItemItemDiffHelper.selectableDrugDtoItemDiffCallback;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.databinding.InventoryItemBinding;
import ch.ffhs.drugstore.shared.dto.management.drugs.SelectableDrugDto;

/**
 * Recycler view list adapter for {@link SelectableDrugDto} items used by {@link
 * InventoryItemHolder} view holder.
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class InventoryListAdapter extends
        ListAdapter<SelectableDrugDto, InventoryItemHolder> {
    private OnHistoryItemClickListener clickListener;

    /**
     * Constructs a {@link InventoryListAdapter}
     */
    @Inject
    public InventoryListAdapter() {
        super(selectableDrugDtoItemDiffCallback);
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
     * Method to update all CheckBoxes on the inventory list
     *
     * @param checkState state to set checkbox to
     */
    @SuppressLint("NotifyDataSetChanged")
    public void toggleCheckAll(boolean checkState) {
        List<SelectableDrugDto> drugDtoList = getCurrentList();
        for (SelectableDrugDto drug : drugDtoList) {
            drug.setSelected(checkState);
            this.clickListener.onItemClick(drug, checkState);
        }
        notifyDataSetChanged();
    }

    /**
     * @param position The position of the item within the adapter's data set
     * @return The item at specified position
     */
    @Override
    protected SelectableDrugDto getItem(int position) {
        return super.getItem(position);
    }
}
