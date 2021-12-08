package ch.ffhs.drugstore.presentation.management.signature.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import javax.inject.Inject;

import ch.ffhs.drugstore.data.dto.SignatureDto;
import ch.ffhs.drugstore.data.dto.SignatureWithDrugs;
import ch.ffhs.drugstore.databinding.SignatureItemBinding;

public class SignatureListAdapter
        extends ListAdapter<SignatureWithDrugs, SignatureListAdapter.SignatureItemHolder> {
    private static final DiffUtil.ItemCallback<SignatureWithDrugs> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<SignatureWithDrugs>() {
                @Override
                public boolean areItemsTheSame(@NonNull SignatureWithDrugs oldItem,
                        @NonNull SignatureWithDrugs newItem) {
                    return oldItem.signature.getSignatureId() == newItem.signature.getSignatureId();
                }

                @Override
                public boolean areContentsTheSame(@NonNull SignatureWithDrugs oldItem,
                        @NonNull SignatureWithDrugs newItem) {
                    return oldItem.signature.getUserId() == newItem.signature.getUserId()
                            && oldItem.signature.getSignatureId()
                            == newItem.signature.getSignatureId()
                            && oldItem.signature.getCreatedAt().equals(
                            newItem.signature.getCreatedAt());
                }
            };

    private SignatureListAdapter.OnItemClickListener clickListener;

    @Inject
    public SignatureListAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public SignatureItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SignatureItemBinding binding =
                SignatureItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent,
                        false);
        return new SignatureItemHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SignatureItemHolder holder, int position) {
        holder.bind(position);
    }

    // allows clicks events to be caught
    public void setClickListener(OnItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(SignatureWithDrugs signature);

        void onItemLongClick(SignatureWithDrugs signature);
    }

    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder).
     */
    public class SignatureItemHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener {
        private final TextView title;
        private final TextView secondary;

        SignatureItemHolder(SignatureItemBinding binding) {
            super(binding.getRoot());
            title = binding.title;
            secondary = binding.secondary;
        }

        void bind(int position) {
            title.setText(String.format("Signature Id: %d",
                    getItem(position).signature.getSignatureId()));
            secondary.setText(
                    String.format("Created at: %s", getItem(
                            position).signature.getCreatedAt().toString()));
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
