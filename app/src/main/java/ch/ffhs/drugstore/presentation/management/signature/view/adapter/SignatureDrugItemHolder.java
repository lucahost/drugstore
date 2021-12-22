package ch.ffhs.drugstore.presentation.management.signature.view.adapter;

import android.content.Context;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ch.ffhs.drugstore.R;
import ch.ffhs.drugstore.databinding.SignatureDrugItemBinding;

/**
 * Recycler view item holder for {@link SignatureDetailListAdapter} items
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class SignatureDrugItemHolder extends RecyclerView.ViewHolder {
    private final SignatureDetailListAdapter signatureDetailListAdapter;
    private final TextView title;
    private final TextView secondary;
    private final TextView chip;
    private final Context context;

    /**
     * Constructs a {@link SignatureDrugItemHolder}
     *
     * @param signatureDetailListAdapter the list adapter
     * @param binding                    the view binding
     */
    SignatureDrugItemHolder(
            SignatureDetailListAdapter signatureDetailListAdapter,
            SignatureDrugItemBinding binding) {
        super(binding.getRoot());
        this.context = binding.getRoot().getContext();
        this.signatureDetailListAdapter = signatureDetailListAdapter;
        title = binding.title;
        secondary = binding.secondary;
        chip = binding.chip;
    }

    /**
     * Set the initial values and listeners on the view binding.
     *
     * @param position The position of the item within the adapter's data set.
     */
    protected void bind(int position) {
        title.setText(getItemTitleText(position));
        secondary.setText(getItemSecondaryText(position));
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
                signatureDetailListAdapter.getItem(position).getDrug().getTitle(),
                signatureDetailListAdapter.getItem(position).getDrug().getDosage());
    }

    /**
     * @param position The position of the item within the adapter's data set.
     * @return secondary text
     */
    @NonNull
    private String getItemSecondaryText(int position) {
        return context.getString(
                R.string.drug_tolerance,
                signatureDetailListAdapter.getItem(position).getDrug().getTolerance());
    }

    /**
     * @param position The position of the item within the adapter's data set.
     * @return chip text
     */
    @NonNull
    private String getItemChipText(int position) {
        return context.getString(
                R.string.actual_vs_expected_units,
                signatureDetailListAdapter.getItem(position).getActualAmount(),
                signatureDetailListAdapter.getItem(position).getExpectedAmount(),
                signatureDetailListAdapter.getItem(position).getDrug().getUnit());
    }
}
