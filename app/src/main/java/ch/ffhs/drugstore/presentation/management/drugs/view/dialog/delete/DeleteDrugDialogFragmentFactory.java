package ch.ffhs.drugstore.presentation.management.drugs.view.dialog.delete;

import dagger.assisted.Assisted;
import dagger.assisted.AssistedFactory;

/**
 * Assisted factory to create {@link DeleteDrugDialogFragment} with {@link
 * DeleteDrugDialogFragmentArgs}.
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
@AssistedFactory
public interface DeleteDrugDialogFragmentFactory {
    /**
     * @param args the arguments to supply to {@link DeleteDrugDialogFragment}
     * @return dialog fragment
     */
    DeleteDrugDialogFragment create(
            @Assisted("deleteDrugDialogFragmentArgs") DeleteDrugDialogFragmentArgs args);
}
