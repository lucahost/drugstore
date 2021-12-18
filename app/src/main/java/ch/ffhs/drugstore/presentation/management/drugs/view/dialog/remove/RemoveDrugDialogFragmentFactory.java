package ch.ffhs.drugstore.presentation.management.drugs.view.dialog.remove;

import dagger.assisted.Assisted;
import dagger.assisted.AssistedFactory;

/**
 * Assisted factory to create {@link RemoveDrugDialogFragment} with {@link
 * RemoveDrugDialogFragmentArgs}.
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
@AssistedFactory
public interface RemoveDrugDialogFragmentFactory {
    /**
     * @param args the arguments to supply to {@link RemoveDrugDialogFragment}
     * @return dialog fragment
     */
    RemoveDrugDialogFragment create(
            @Assisted("removeDrugDialogFragmentArgs") RemoveDrugDialogFragmentArgs args);
}
