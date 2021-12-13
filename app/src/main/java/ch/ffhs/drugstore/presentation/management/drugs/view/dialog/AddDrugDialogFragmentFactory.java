package ch.ffhs.drugstore.presentation.management.drugs.view.dialog;

import ch.ffhs.drugstore.presentation.dispensary.view.dialog.DispenseDrugDialogFragment;
import ch.ffhs.drugstore.presentation.dispensary.view.dialog.DispenseDrugDialogFragmentArgs;
import dagger.assisted.Assisted;
import dagger.assisted.AssistedFactory;

@AssistedFactory
public interface AddDrugDialogFragmentFactory {
    AddDrugDialogFragment create(@Assisted("addDrugDialogFragmentArgs") AddDrugDialogFragmentArgs args);
}
