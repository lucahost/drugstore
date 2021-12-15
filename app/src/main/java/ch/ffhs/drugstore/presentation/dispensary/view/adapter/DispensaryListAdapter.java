package ch.ffhs.drugstore.presentation.dispensary.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import javax.inject.Inject;

import ch.ffhs.drugstore.databinding.DispensaryItemBinding;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugDto;

public class DispensaryListAdapter
        extends ListAdapter<DrugDto, DispensaryListAdapter.DispensaryItemHolder> {
    private static final DiffUtil.ItemCallback<DrugDto> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<DrugDto>() {
                @Override
                public boolean areItemsTheSame(@NonNull DrugDto oldItem, @NonNull DrugDto newItem) {
                    return oldItem.getDrugId() == newItem.getDrugId();
                }

                @Override
                public boolean areContentsTheSame(@NonNull DrugDto oldItem,
                        @NonNull DrugDto newItem) {
                    return oldItem.getTitle().equals(newItem.getTitle())
                            && oldItem.getDrugType().equals(newItem.getDrugType())
                            && oldItem.getSubstance().equals(newItem.getSubstance())
                            && oldItem.getDosage().equals(newItem.getDosage())
                            && oldItem.getTolerance() == newItem.getTolerance()
                            && oldItem.getStockAmount() == newItem.getStockAmount()
                            && oldItem.isFavorite() == newItem.isFavorite();
                }
            };

    private DispensaryListAdapter.OnItemClickListener clickListener;

    @Inject
    public DispensaryListAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public DispensaryItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        DispensaryItemBinding binding =
                DispensaryItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent,
                        false);
        return new DispensaryItemHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DispensaryItemHolder holder, int position) {
        holder.bind(position);
    }

    // allows clicks events to be caught
    public void setClickListener(OnItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(DrugDto drug);

        void onItemLongClick(DrugDto drug);
    }

    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder).
     */
    public class DispensaryItemHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener {
        private final MaterialCardView card;
        private final TextView drugTitle;
        private final TextView drugSecondary;

        DispensaryItemHolder(DispensaryItemBinding binding) {
            super(binding.getRoot());
            drugTitle = binding.drugTitle;
            drugSecondary = binding.drugSecondary;
            card = binding.card;
        }

        void bind(int position) {
            drugTitle.setText(String.format("%s - %s (%s)", getItem(position).getDrugId(),
                    getItem(position).getTitle(), getItem(position).getDrugType()));
            drugSecondary.setText(getItem(position).getDosage());
            card.setChecked(getItem(position).isFavorite());
            card.setOnClickListener(this);
            card.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onItemClick(getItem(getAdapterPosition()));
        }

        @Override
        public boolean onLongClick(View view) {
            if (clickListener != null) clickListener.onItemLongClick(getItem(getAdapterPosition()));
            return true;
        }
    }
}
