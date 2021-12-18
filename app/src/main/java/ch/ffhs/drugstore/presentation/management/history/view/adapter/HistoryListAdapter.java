package ch.ffhs.drugstore.presentation.management.history.view.adapter;

import static ch.ffhs.drugstore.presentation.management.ListItemItemDiffHelper.transactionDtoItemDiffCallback;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.ListAdapter;

import javax.inject.Inject;

import ch.ffhs.drugstore.databinding.HistoryItemBinding;
import ch.ffhs.drugstore.shared.dto.management.history.TransactionDto;

/**
 * Recycler view list adapter for {@link TransactionDto} items used by {@link HistoryItemHolder}
 * view holder.
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class HistoryListAdapter
        extends ListAdapter<TransactionDto, HistoryItemHolder> {

    /**
     * Constructs a {@link HistoryListAdapter}
     */
    @Inject
    public HistoryListAdapter() {
        super(transactionDtoItemDiffCallback);
    }

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    public HistoryItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        HistoryItemBinding binding =
                HistoryItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new HistoryItemHolder(this, binding);
    }

    /**
     * {@inheritDoc}
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull HistoryItemHolder holder, int position) {
        holder.bind(position);
    }

    /**
     * @param position The position of the item within the adapter's data set
     * @return The item at specified position
     */
    protected TransactionDto getItem(int position) {
        return super.getItem(position);
    }
}
