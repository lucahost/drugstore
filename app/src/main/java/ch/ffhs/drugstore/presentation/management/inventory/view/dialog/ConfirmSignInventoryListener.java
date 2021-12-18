package ch.ffhs.drugstore.presentation.management.inventory.view.dialog;

/**
 * Confirmation listener interface for signing inventories
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public interface ConfirmSignInventoryListener {
    /**
     * Confirm signing of an inventory
     *
     * @param employee the employee signing the inventory
     */
    void onConfirmSignInventory(String employee);
}
