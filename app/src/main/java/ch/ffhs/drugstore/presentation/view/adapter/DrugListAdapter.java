package ch.ffhs.drugstore.presentation.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import javax.inject.Inject;

import ch.ffhs.drugstore.data.entity.Drug;
import ch.ffhs.drugstore.databinding.DrugItemBinding;

public class DrugListAdapter extends ListAdapter<Drug, DrugListAdapter.DrugHolder> {
    private static final DiffUtil.ItemCallback<Drug> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Drug>() {
                @Override
                public boolean areItemsTheSame(@NonNull Drug oldItem, @NonNull Drug newItem) {
                    return oldItem.getDrugId() == newItem.getDrugId();
                }

                @Override
                public boolean areContentsTheSame(@NonNull Drug oldItem, @NonNull Drug newItem) {
                    return oldItem.getTitle().equals(newItem.getTitle())
                            && oldItem.getDosage().equals(newItem.getDosage());
                }
            };

    @Inject
    public DrugListAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public DrugHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        DrugItemBinding binding =
                DrugItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new DrugListAdapter.DrugHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DrugHolder holder, int position) {
        holder.bind(position);
    }

    /** Provide a reference to the type of views that you are using (custom ViewHolder). */
    public class DrugHolder extends RecyclerView.ViewHolder {
        private final MaterialCardView card;
        private final TextView drugTitle;
        private final TextView drugSecondary;

        DrugHolder(DrugItemBinding binding) {
            super(binding.getRoot());
            drugTitle = binding.drugTitle;
            drugSecondary = binding.drugSecondary;
            card = binding.card;
        }

        void bind(int position) {
            drugTitle.setText(getItem(position).getTitle());
            drugSecondary.setText(getItem(position).getDosage());
            card.setChecked(getItem(position).isFavorite());
        }
    }
}
