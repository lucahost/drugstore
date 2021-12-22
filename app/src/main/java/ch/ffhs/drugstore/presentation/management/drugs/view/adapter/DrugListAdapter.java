package ch.ffhs.drugstore.presentation.management.drugs.view.adapter;

import static ch.ffhs.drugstore.presentation.helpers.ListItemItemDiffHelper.drugDtoItemDiffCallback;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;

import javax.inject.Inject;

import ch.ffhs.drugstore.databinding.DrugItemBinding;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugDto;

/**
 * Recycler view list adapter for {@link DrugDto} items used by {@link DrugItemHolder} view holder.
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class DrugListAdapter extends ListAdapter<DrugDto, DrugItemHolder> {
    private OnDrugListItemClickListener clickListener;

    /**
     * Constructs a {@link DrugListAdapter}
     */
    @Inject
    public DrugListAdapter() {
        super(drugDtoItemDiffCallback);
    }

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    public DrugItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        DrugItemBinding binding =
                DrugItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new DrugItemHolder(this, binding);
    }

    /**
     * @return click listener
     */
    public OnDrugListItemClickListener getClickListener() {
        return clickListener;
    }

    /**
     * allows clicks events to be caught
     *
     * @param itemClickListener click listener
     */
    public void setClickListener(OnDrugListItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onBindViewHolder(@NonNull DrugItemHolder holder, int position) {
        holder.bind(position);
    }

    /**
     * @param position The position of the item within the adapter's data set
     * @return The item at specified position
     */
    @Override
    public DrugDto getItem(int position) {
        return super.getItem(position);
    }
}
