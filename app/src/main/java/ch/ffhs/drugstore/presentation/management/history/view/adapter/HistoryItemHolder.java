package ch.ffhs.drugstore.presentation.management.history.view.adapter;

import android.content.Context;
import android.os.Build;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import ch.ffhs.drugstore.R;
import ch.ffhs.drugstore.databinding.HistoryItemBinding;

/**
 * Recycler view item holder for {@link HistoryListAdapter} items
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class HistoryItemHolder extends RecyclerView.ViewHolder {
    private final HistoryListAdapter historyListAdapter;
    private final TextView title;
    private final TextView secondary;
    private final TextView tertiary;
    private final Chip chip;
    private final Context context;

    /**
     * Constructs a {@link HistoryItemHolder}
     *
     * @param historyListAdapter the list adapter
     * @param binding            the view binding
     */
    HistoryItemHolder(HistoryListAdapter historyListAdapter, HistoryItemBinding binding) {
        super(binding.getRoot());
        this.context = binding.getRoot().getContext();
        this.historyListAdapter = historyListAdapter;
        title = binding.title;
        secondary = binding.secondary;
        tertiary = binding.tertiary;
        chip = binding.chip;
    }

    /**
     * Set the initial values and listeners on the view binding.
     *
     * @param position The position of the item within the adapter's data set.
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    protected void bind(int position) {
        title.setText(getItemTitleText(position));
        secondary.setText(getItemSecondaryText(position));
        tertiary.setText(getItemTertiaryText(position));
        chip.setText(getItemChipText(position));
    }

    /**
     * @param position The position of the item within the adapter's data set.
     * @return title text
     */
    @NonNull
    private String getItemTitleText(int position) {
        return context.getString(
                R.string.drug_title_and_dosage,
                historyListAdapter.getItem(position).getDrug().getTitle(),
                historyListAdapter.getItem(position).getDrug().getDosage());
    }

    /**
     * @param position The position of the item within the adapter's data set.
     * @return secondary text
     */
    @NonNull
    private String getItemSecondaryText(int position) {
        return context.getString(
                R.string.from_to,
                historyListAdapter.getItem(position).getUser().getShortName(),
                historyListAdapter.getItem(position).getPatient());
    }

    /**
     * @param position The position of the item within the adapter's data set.
     * @return tertiary text
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    @NonNull
    private String getItemTertiaryText(int position) {
        ZonedDateTime createdAt = historyListAdapter.getItem(position).getCreatedAt();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedDateTime(
                FormatStyle.MEDIUM);
        return createdAt.format(dateTimeFormatter);
    }

    /**
     * @param position The position of the item within the adapter's data set.
     * @return chip text
     */
    @NonNull
    private String getItemChipText(int position) {
        return context.getString(
                R.string.drug_amount_and_unit,
                historyListAdapter.getItem(position).getAmount(),
                historyListAdapter.getItem(position).getDrug().getUnit());
    }
}
