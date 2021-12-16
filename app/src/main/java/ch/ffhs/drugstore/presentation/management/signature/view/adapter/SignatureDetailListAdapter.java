package ch.ffhs.drugstore.presentation.management.signature.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Locale;

import javax.inject.Inject;

import ch.ffhs.drugstore.databinding.SignatureDrugItemBinding;
import ch.ffhs.drugstore.shared.dto.management.signature.SignatureDrugDto;

public class SignatureDetailListAdapter
        extends ListAdapter<SignatureDrugDto, SignatureDetailListAdapter.SignatureDrugItemHolder> {
    private static final DiffUtil.ItemCallback<SignatureDrugDto> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<SignatureDrugDto>() {
                @Override
                public boolean areItemsTheSame(@NonNull SignatureDrugDto oldItem,
                        @NonNull SignatureDrugDto newItem) {
                    return oldItem.getSignatureId() == newItem.getSignatureId();
                }

                @Override
                public boolean areContentsTheSame(@NonNull SignatureDrugDto oldItem,
                        @NonNull SignatureDrugDto newItem) {
                    return oldItem.getActualAmount() == newItem.getActualAmount()
                            && oldItem.getExpectedAmount() == newItem.getExpectedAmount()
                            && oldItem.getDrugId() == newItem.getDrugId();
                }
            };

    private SignatureDetailListAdapter.OnItemClickListener clickListener;

    @Inject
    public SignatureDetailListAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public SignatureDrugItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SignatureDrugItemBinding binding =
                SignatureDrugItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent,
                        false);
        return new SignatureDrugItemHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SignatureDrugItemHolder holder, int position) {
        holder.bind(position);
    }

    // allows clicks events to be caught
    public void setClickListener(OnItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, SignatureDrugDto signatureDrugDto);
    }

    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder).
     */
    public class SignatureDrugItemHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        private final TextView title;
        private final TextView secondary;

        SignatureDrugItemHolder(SignatureDrugItemBinding binding) {
            super(binding.getRoot());
            title = binding.title;
            secondary = binding.secondary;
        }

        void bind(int position) {
            title.setText(getItem(position).getDrug().getTitle());
            secondary.setText(String.format(Locale.getDefault(), "Actual: %.2f Expected: %.2f",
                    getItem(position).getActualAmount(), getItem(position).getExpectedAmount()));
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onItemClick(view, getItem(getAdapterPosition()));
        }
    }
}
