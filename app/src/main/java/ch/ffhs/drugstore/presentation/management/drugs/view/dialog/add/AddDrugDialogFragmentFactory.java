package ch.ffhs.drugstore.presentation.management.drugs.view.dialog.add;

import dagger.assisted.Assisted;
import dagger.assisted.AssistedFactory;

/**
 * Assisted factory to create {@link AddDrugDialogFragmentFactory} with {@link
 * AddDrugDialogFragmentArgs}.
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
@AssistedFactory
public interface AddDrugDialogFragmentFactory {
    /**
     * @param args the arguments to supply to {@link AddDrugDialogFragment}
     * @return dialog fragment
     */
    AddDrugDialogFragment create(
            @Assisted("addDrugDialogFragmentArgs") AddDrugDialogFragmentArgs args);
}
