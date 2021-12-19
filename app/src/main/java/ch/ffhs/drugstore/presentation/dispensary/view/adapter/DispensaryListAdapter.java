package ch.ffhs.drugstore.presentation.dispensary.view.adapter;

import static ch.ffhs.drugstore.presentation.management.ListItemItemDiffHelper.drugDtoItemDiffCallback;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import javax.inject.Inject;

import ch.ffhs.drugstore.databinding.DispensaryItemBinding;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugDto;

/**
 * Recycler view list adapter for {@link DrugDto} items used by {@link DispensaryItemHolder} view
 * holder.
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class DispensaryListAdapter
        extends ListAdapter<DrugDto, DispensaryItemHolder> {
    private OnDispensaryItemClickListener clickListener;

    /**
     * Construct a {@link DispensaryListAdapter}
     */
    @Inject
    public DispensaryListAdapter() {
        super((DiffUtil.ItemCallback<DrugDto>) drugDtoItemDiffCallback);
    }

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    public DispensaryItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        DispensaryItemBinding binding =
                DispensaryItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent,
                        false);
        return new DispensaryItemHolder(this, binding);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onBindViewHolder(@NonNull DispensaryItemHolder holder, int position) {
        holder.bind(position);
    }

    /**
     * @return click listener
     */
    protected OnDispensaryItemClickListener getClickListener() {
        return clickListener;
    }

    /**
     * allows clicks events to be caught
     *
     * @param itemClickListener click listener
     */
    public void setClickListener(OnDispensaryItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    /**
     * @param position The position of the item within the adapter's data set
     * @return The item at specified position
     */
    protected DrugDto getItem(int position) {
        return super.getItem(position);
    }
}
