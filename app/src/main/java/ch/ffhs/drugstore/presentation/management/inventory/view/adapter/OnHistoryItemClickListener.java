package ch.ffhs.drugstore.presentation.management.inventory.view.adapter;

import ch.ffhs.drugstore.shared.dto.management.drugs.SelectableDrugDto;

/**
 * Item click listener interface for history items
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public interface OnHistoryItemClickListener {
    /**
     * Called when a {@link SelectableDrugDto} item has been clicked.
     *
     * @param drugDto the clicked {@link SelectableDrugDto} item
     * @param selected state of the checkbox
     */
    void onItemClick(SelectableDrugDto drugDto, boolean selected);
}
