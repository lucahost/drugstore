package ch.ffhs.drugstore.presentation.dispensary.view.adapter;

import ch.ffhs.drugstore.shared.dto.management.drugs.DrugDto;

/**
 * Item click listener interface for dispensary items
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public interface OnDispensaryItemClickListener {
    /**
     * Called when a {@link DrugDto} item has been clicked.
     *
     * @param drug the clicked {@link DrugDto} item
     */
    void onItemClick(DrugDto drug);

    /**
     * Called when a {@link DrugDto} has been clicked and held.
     *
     * @param drug the clicked and held {@link DrugDto} item
     */
    void onItemLongClick(DrugDto drug);
}
