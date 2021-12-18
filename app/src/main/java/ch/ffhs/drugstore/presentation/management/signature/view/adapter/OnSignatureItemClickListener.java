package ch.ffhs.drugstore.presentation.management.signature.view.adapter;

import android.view.View;

import ch.ffhs.drugstore.shared.dto.management.signature.SignatureDto;

/**
 * Item click listener interface for signature items
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public interface OnSignatureItemClickListener {
    /**
     * Called when a {@link SignatureDto} item has been clicked.
     *
     * @param signature the clicked {@link SignatureDto} item
     */
    void onItemClick(View view, SignatureDto signature);
}
