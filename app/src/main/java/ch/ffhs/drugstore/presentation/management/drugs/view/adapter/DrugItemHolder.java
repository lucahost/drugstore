package ch.ffhs.drugstore.presentation.management.drugs.view.adapter;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import ch.ffhs.drugstore.databinding.DrugItemBinding;

/**
 * Recycler view item holder for {@link DrugListAdapter} items
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class DrugItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final DrugListAdapter drugListAdapter;
    private final TextView title;
    private final TextView secondary;
    private final ImageButton itemAction;

    /**
     * Constructs a {@link DrugItemHolder}
     *
     * @param drugListAdapter the list adapter
     * @param binding         the view binding
     */
    DrugItemHolder(DrugListAdapter drugListAdapter, DrugItemBinding binding) {
        super(binding.getRoot());
        this.drugListAdapter = drugListAdapter;
        title = binding.title;
        secondary = binding.secondary;
        itemAction = binding.itemAction;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onClick(View view) {
        if (drugListAdapter.getClickListener() != null) {
            drugListAdapter.getClickListener().onItemClick(itemAction,
                    drugListAdapter.getItem(getAdapterPosition()));
        }
    }

    /**
     * Set the initial values and listeners on the view binding.
     *
     * @param position The position of the item within the adapter's data set.
     */
    protected void bind(int position) {
        title.setText(drugListAdapter.getItem(position).getTitle());
        secondary.setText(drugListAdapter.getItem(position).getDosage());
        itemView.setOnClickListener(this);
        itemAction.setOnClickListener(this);
    }
}
