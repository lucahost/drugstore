package ch.ffhs.drugstore.presentation.dispensary.view.dialog;

import dagger.assisted.Assisted;
import dagger.assisted.AssistedFactory;

@AssistedFactory
public interface DispenseDrugDialogFragmentFactory {
    DispenseDrugDialogFragment create(@Assisted("dispenseDrugDialogFragmentArgs") DispenseDrugDialogFragmentArgs args);
}
