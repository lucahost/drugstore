package ch.ffhs.drugstore.presentation.management.drugs.view.dialog.edit;

import dagger.assisted.Assisted;
import dagger.assisted.AssistedFactory;

/**
 * Assisted factory to create {@link EditDrugDialogFragment} with {@link
 * EditDrugDialogFragmentArgs}.
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
@AssistedFactory
public interface EditDrugDialogFragmentFactory {
    /**
     * @param args the arguments to supply to {@link EditDrugDialogFragment}
     * @return dialog fragment
     */
    EditDrugDialogFragment create(
            @Assisted("editDrugDialogFragmentArgs") EditDrugDialogFragmentArgs args);
}
