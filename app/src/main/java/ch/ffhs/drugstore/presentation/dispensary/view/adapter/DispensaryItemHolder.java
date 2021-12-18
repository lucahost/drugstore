package ch.ffhs.drugstore.presentation.dispensary.view.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.text.DecimalFormat;

import ch.ffhs.drugstore.R;
import ch.ffhs.drugstore.databinding.DispensaryItemBinding;

/**
 * Recycler view item holder for {@link DispensaryListAdapter} items
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class DispensaryItemHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener, View.OnLongClickListener {
    private final DispensaryListAdapter dispensaryListAdapter;
    private final MaterialCardView card;
    private final TextView drugTitle;
    private final TextView drugSecondary;

    /**
     * Constructs a {@link DispensaryItemHolder}
     *
     * @param dispensaryListAdapter the list adapter
     * @param binding               the view binding
     */
    DispensaryItemHolder(DispensaryListAdapter dispensaryListAdapter,
            DispensaryItemBinding binding) {
        super(binding.getRoot());
        this.dispensaryListAdapter = dispensaryListAdapter;
        drugTitle = binding.drugTitle;
        drugSecondary = binding.drugSecondary;
        card = binding.card;
    }

    /**
     * Set the initial values and listeners on the view binding.
     *
     * @param position The position of the item within the adapter's data set.
     */
    protected void bind(int position) {
        drugTitle.setText(
                String.format("%s (%s)", dispensaryListAdapter.getItem(position).getTitle(),
                        dispensaryListAdapter.getItem(position).getDrugType()));
        drugSecondary.setText(
                String.format("%s - %s %s %s", dispensaryListAdapter.getItem(position).getDosage(),
                        card.getContext().getResources().getString(R.string.remaining),
                        new DecimalFormat("#.##").format(
                                dispensaryListAdapter.getItem(position).getStockAmount()),
                        dispensaryListAdapter.getItem(position).getUnit()));
        card.setChecked(dispensaryListAdapter.getItem(position).isFavorite());
        card.setOnClickListener(this);
        card.setOnLongClickListener(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onClick(View view) {
        if (dispensaryListAdapter.getClickListener() != null) {
            dispensaryListAdapter.getClickListener().onItemClick(
                    dispensaryListAdapter.getItem(getAdapterPosition()));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean onLongClick(View view) {
        if (dispensaryListAdapter.getClickListener() != null) {
            dispensaryListAdapter.getClickListener().onItemLongClick(
                    dispensaryListAdapter.getItem(getAdapterPosition()));
        }
        return true;
    }
}
