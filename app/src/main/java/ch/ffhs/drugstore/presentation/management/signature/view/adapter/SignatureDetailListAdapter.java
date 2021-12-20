package ch.ffhs.drugstore.presentation.management.signature.view.adapter;

import static ch.ffhs.drugstore.presentation.helpers.ListItemItemDiffHelper.signatureDrugDtoItemDiffCallback;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;

import javax.inject.Inject;

import ch.ffhs.drugstore.databinding.SignatureDrugItemBinding;
import ch.ffhs.drugstore.shared.dto.management.signature.SignatureDrugDto;

/**
 * Recycler view list adapter for {@link SignatureDrugDto} items used by {@link
 * SignatureDrugItemHolder} view holder.
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class SignatureDetailListAdapter
        extends ListAdapter<SignatureDrugDto, SignatureDrugItemHolder> {
    /**
     * Constructs a {@link SignatureDetailListAdapter}
     */
    @Inject
    public SignatureDetailListAdapter() {
        super(signatureDrugDtoItemDiffCallback);
    }

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    public SignatureDrugItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SignatureDrugItemBinding binding =
                SignatureDrugItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent,
                        false);
        return new SignatureDrugItemHolder(this, binding);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onBindViewHolder(@NonNull SignatureDrugItemHolder holder, int position) {
        holder.bind(position);
    }

    /**
     * @param position The position of the item within the adapter's data set
     * @return The item at specified position
     */
    protected SignatureDrugDto getItem(int position) {
        return super.getItem(position);
    }
}
