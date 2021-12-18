package ch.ffhs.drugstore.presentation.dispensary.view.dialog;

import dagger.assisted.Assisted;
import dagger.assisted.AssistedFactory;

/**
 * Assisted factory to create {@link DispenseDrugDialogFragment} with {@link
 * DispenseDrugDialogFragmentArgs}.
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
@AssistedFactory
public interface DispenseDrugDialogFragmentFactory {
    /**
     * @param args the arguments to supply to {@link DispenseDrugDialogFragment}
     * @return dialog fragment
     */
    DispenseDrugDialogFragment create(
            @Assisted("dispenseDrugDialogFragmentArgs") DispenseDrugDialogFragmentArgs args);
}
