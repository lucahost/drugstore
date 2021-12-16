package ch.ffhs.drugstore.presentation.management.signature.view.adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
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
import java.util.Locale;

import javax.inject.Inject;

import ch.ffhs.drugstore.R;
import ch.ffhs.drugstore.data.relation.SignatureWithUserAndSignatureDrugsAndDrugs;
import ch.ffhs.drugstore.databinding.SignatureItemBinding;

public class SignatureListAdapter
        extends
        ListAdapter<SignatureWithUserAndSignatureDrugsAndDrugs,
                SignatureListAdapter.SignatureItemHolder> {
    private static final DiffUtil.ItemCallback<SignatureWithUserAndSignatureDrugsAndDrugs>
            DIFF_CALLBACK =
            new DiffUtil.ItemCallback<SignatureWithUserAndSignatureDrugsAndDrugs>() {
                @Override
                public boolean areItemsTheSame(
                        @NonNull SignatureWithUserAndSignatureDrugsAndDrugs oldItem,
                        @NonNull SignatureWithUserAndSignatureDrugsAndDrugs newItem) {
                    return oldItem.signature.getSignatureId() == newItem.signature.getSignatureId();
                }

                @Override
                public boolean areContentsTheSame(
                        @NonNull SignatureWithUserAndSignatureDrugsAndDrugs oldItem,
                        @NonNull SignatureWithUserAndSignatureDrugsAndDrugs newItem) {
                    return oldItem.signature.getUserId() == newItem.signature.getUserId()
                            && oldItem.signature.getSignatureId()
                            == newItem.signature.getSignatureId()
                            && oldItem.signature.getCreatedAt().equals(
                            newItem.signature.getCreatedAt());
                }
            };

    private SignatureListAdapter.OnItemClickListener clickListener;
    private Context context;

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
        context = binding.getRoot().getContext();
        return new SignatureItemHolder(binding);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull SignatureItemHolder holder, int position) {
        holder.bind(position);
    }

    // allows clicks events to be caught
    public void setClickListener(OnItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, SignatureWithUserAndSignatureDrugsAndDrugs signature);
    }

    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder).
     */
    public class SignatureItemHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        private final TextView title;
        private final TextView secondary;
        private final Chip chip;

        SignatureItemHolder(SignatureItemBinding binding) {
            super(binding.getRoot());
            title = binding.title;
            secondary = binding.secondary;
            chip = binding.chip;
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        void bind(int position) {
            title.setText(getItemTitleText(position));
            secondary.setText(getItemSecondaryText(position));
            chip.setText(getItemChipText(position));
            itemView.setOnClickListener(this);
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        @NonNull
        private String getItemTitleText(int position) {
            ZonedDateTime createdAt = getItem(position).signature.getCreatedAt();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedDateTime(
                    FormatStyle.MEDIUM);
            return createdAt.format(dateTimeFormatter);
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        @NonNull
        private String getItemSecondaryText(int position) {
            return context.getString(R.string.drug_count,
                    getItem(position).signatureDrugs.size());
        }

        @NonNull
        private String getItemChipText(int position) {
            return getItem(position).user.getShortName();
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) {
                clickListener.onItemClick(view,
                        getItem(getAdapterPosition()));
            }
        }
    }
}
