package ch.ffhs.drugstore.presentation.management.history.view.adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import javax.inject.Inject;

import ch.ffhs.drugstore.R;
import ch.ffhs.drugstore.databinding.HistoryItemBinding;
import ch.ffhs.drugstore.shared.dto.management.history.TransactionDto;

public class HistoryListAdapter
        extends ListAdapter<TransactionDto, HistoryListAdapter.HistoryItemHolder> {
    private static final DiffUtil.ItemCallback<TransactionDto> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<TransactionDto>() {
                @Override
                public boolean areItemsTheSame(@NonNull TransactionDto oldItem,
                        @NonNull TransactionDto newItem) {
                    return oldItem.getTransactionId() == newItem.getTransactionId();
                }

                @Override
                public boolean areContentsTheSame(
                        @NonNull TransactionDto oldItem, @NonNull TransactionDto newItem) {
                    return oldItem.getDrug().getDrugId() == newItem.getDrug().getDrugId()
                            && oldItem.getAmount() == newItem.getAmount();
                }
            };

    private Context context;

    @Inject
    public HistoryListAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public HistoryItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        HistoryItemBinding binding =
                HistoryItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        context = binding.getRoot().getContext();
        return new HistoryItemHolder(binding);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull HistoryItemHolder holder, int position) {
        holder.bind(position);
    }

    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder).
     */
    public class HistoryItemHolder extends RecyclerView.ViewHolder {
        private final TextView title;
        private final TextView secondary;
        private final TextView tertiary;
        private final Chip chip;

        HistoryItemHolder(HistoryItemBinding binding) {
            super(binding.getRoot());
            title = binding.title;
            secondary = binding.secondary;
            tertiary = binding.tertiary;
            chip = binding.chip;
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        void bind(int position) {
            title.setText(getItemTitleText(position));
            secondary.setText(getItemSecondaryText(position));
            tertiary.setText(getItemTertiaryText(position));
            chip.setText(getItemChipText(position));
        }

        @NonNull
        private String getItemTitleText(int position) {
            return context.getString(R.string.drug_title_and_dosage,
                    getItem(position).getDrug().getTitle(),
                    getItem(position).getDrug().getDosage());
        }

        @NonNull
        private String getItemSecondaryText(int position) {
            return context.getString(R.string.from_to,
                    getItem(position).getUser().getShortName(),
                    getItem(position).getPatient());
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        @NonNull
        private String getItemTertiaryText(int position) {
            ZonedDateTime createdAt = getItem(position).getCreatedAt();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedDateTime(
                    FormatStyle.MEDIUM);
            return createdAt.format(dateTimeFormatter);
        }

        @NonNull
        private String getItemChipText(int position) {
            return context.getString(R.string.drug_amount_and_unit,
                    getItem(position).getAmount(),
                    getItem(position).getDrug().getUnit());
        }
    }
}
