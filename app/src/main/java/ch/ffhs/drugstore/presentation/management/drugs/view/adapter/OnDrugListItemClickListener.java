package ch.ffhs.drugstore.presentation.management.drugs.view.adapter;

import android.view.View;

import ch.ffhs.drugstore.shared.dto.management.drugs.DrugDto;

/**
 * Item click listener interface for drug items
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public interface OnDrugListItemClickListener {
    /**
     * Called when a {@link DrugDto} item has been clicked.
     *
     * @param drug the clicked {@link DrugDto} item
     */
    void onItemClick(View view, DrugDto drug);
}
