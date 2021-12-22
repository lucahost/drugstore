package ch.ffhs.drugstore.presentation.management.signature.view.adapter;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import ch.ffhs.drugstore.R;
import ch.ffhs.drugstore.databinding.SignatureItemBinding;

/**
 * Recycler view item holder for {@link SignatureListAdapter} items
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class SignatureItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final SignatureListAdapter signatureListAdapter;
    private final TextView title;
    private final TextView secondary;
    private final Chip chip;
    private final Context context;

    /**
     * Constructs a {@link SignatureItemHolder}
     *
     * @param signatureListAdapter the list adapter
     * @param binding              the view binding
     */
    SignatureItemHolder(SignatureListAdapter signatureListAdapter, SignatureItemBinding binding) {
        super(binding.getRoot());
        this.context = binding.getRoot().getContext();
        this.signatureListAdapter = signatureListAdapter;
        title = binding.title;
        secondary = binding.secondary;
        chip = binding.chip;
    }

    /**
     * Set the initial values and listeners on the view binding.
     *
     * @param position The position of the item within the adapter's data set.
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    void bind(int position) {
        title.setText(getItemTitleText(position));
        secondary.setText(getItemSecondaryText(position));
        chip.setText(getItemChipText(position));
        itemView.setOnClickListener(this);
    }

    /**
     * @param position The position of the item within the adapter's data set.
     * @return title text
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    @NonNull
    private String getItemTitleText(int position) {
        ZonedDateTime createdAt = signatureListAdapter.getItem(position).getCreatedAt();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedDateTime(
                FormatStyle.MEDIUM);
        return createdAt.format(dateTimeFormatter);
    }

    /**
     * @param position The position of the item within the adapter's data set.
     * @return secondary text
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    @NonNull
    private String getItemSecondaryText(int position) {
        int drugCount = signatureListAdapter.getItem(position).getSignatureDrugs().size();
        return context.getResources().getQuantityString(R.plurals.drug_count, drugCount, drugCount);
    }

    /**
     * @param position The position of the item within the adapter's data set.
     * @return chip text
     */
    @NonNull
    private String getItemChipText(int position) {
        return signatureListAdapter.getItem(position).getUser().getShortName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onClick(View view) {
        if (signatureListAdapter.getClickListener() != null) {
            signatureListAdapter
                    .getClickListener()
                    .onItemClick(view, signatureListAdapter.getItem(getAdapterPosition()));
        }
    }
}
