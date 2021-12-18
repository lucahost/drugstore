package ch.ffhs.drugstore.presentation.management.signature.view.adapter;

import static ch.ffhs.drugstore.presentation.management.ListItemItemDiffHelper.signatureDtoItemDiffCallback;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.ListAdapter;

import javax.inject.Inject;

import ch.ffhs.drugstore.databinding.SignatureItemBinding;
import ch.ffhs.drugstore.shared.dto.management.signature.SignatureDto;

/**
 * Recycler view list adapter for {@link SignatureDto} items used by {@link SignatureItemHolder}
 * view holder.
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class SignatureListAdapter extends
        ListAdapter<SignatureDto, SignatureItemHolder> {
    private OnSignatureItemClickListener clickListener;

    /**
     * Constructs a {@link SignatureListAdapter}
     */
    @Inject
    public SignatureListAdapter() {
        super(signatureDtoItemDiffCallback);
    }

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    public SignatureItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SignatureItemBinding binding =
                SignatureItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent,
                        false);
        return new SignatureItemHolder(this, binding);
    }

    /**
     * {@inheritDoc}
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull SignatureItemHolder holder, int position) {
        holder.bind(position);
    }

    /**
     * @return click listener
     */
    public OnSignatureItemClickListener getClickListener() {
        return clickListener;
    }

    /**
     * allows clicks events to be caught
     *
     * @param itemClickListener click listener
     */
    public void setClickListener(OnSignatureItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    /**
     * @param position The position of the item within the adapter's data set
     * @return The item at specified position
     */
    protected SignatureDto getItem(int position) {
        return super.getItem(position);
    }
}
