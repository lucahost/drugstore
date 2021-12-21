package ch.ffhs.drugstore.presentation.dialog;

import androidx.fragment.app.FragmentManager;

import javax.inject.Inject;

import ch.ffhs.drugstore.presentation.dispensary.view.dialog.DispenseDrugDialogFragment;
import ch.ffhs.drugstore.presentation.dispensary.view.dialog.DispenseDrugDialogFragmentArgs;
import ch.ffhs.drugstore.presentation.dispensary.view.dialog.DispenseDrugDialogFragmentFactory;
import ch.ffhs.drugstore.presentation.management.drugs.view.dialog.add.AddDrugDialogFragment;
import ch.ffhs.drugstore.presentation.management.drugs.view.dialog.add.AddDrugDialogFragmentArgs;
import ch.ffhs.drugstore.presentation.management.drugs.view.dialog.add.AddDrugDialogFragmentFactory;
import ch.ffhs.drugstore.presentation.management.drugs.view.dialog.create.CreateDrugDialogFragment;
import ch.ffhs.drugstore.presentation.management.drugs.view.dialog.delete.DeleteDrugDialogFragment;
import ch.ffhs.drugstore.presentation.management.drugs.view.dialog.delete.DeleteDrugDialogFragmentArgs;
import ch.ffhs.drugstore.presentation.management.drugs.view.dialog.delete.DeleteDrugDialogFragmentFactory;
import ch.ffhs.drugstore.presentation.management.drugs.view.dialog.edit.EditDrugDialogFragment;
import ch.ffhs.drugstore.presentation.management.drugs.view.dialog.edit.EditDrugDialogFragmentArgs;
import ch.ffhs.drugstore.presentation.management.drugs.view.dialog.edit.EditDrugDialogFragmentFactory;
import ch.ffhs.drugstore.presentation.management.drugs.view.dialog.remove.RemoveDrugDialogFragment;
import ch.ffhs.drugstore.presentation.management.drugs.view.dialog.remove.RemoveDrugDialogFragmentArgs;
import ch.ffhs.drugstore.presentation.management.drugs.view.dialog.remove.RemoveDrugDialogFragmentFactory;
import ch.ffhs.drugstore.presentation.management.inventory.view.dialog.SignInventoryDialogFragment;

/**
 * Service to handle opening and closing of app dialogs.
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class DialogService {

  @Inject protected AddDrugDialogFragmentFactory addDrugDialogFragmentFactory;
  @Inject protected DeleteDrugDialogFragmentFactory deleteDrugDialogFragmentFactory;
  @Inject protected EditDrugDialogFragmentFactory editDrugDialogFragmentFactory;
  @Inject protected RemoveDrugDialogFragmentFactory removeDrugDialogFragmentFactory;
  @Inject protected DispenseDrugDialogFragmentFactory dispenseDrugDialogFragmentFactory;
  @Inject protected CreateDrugDialogFragment createDrugDialogFragment;
  @Inject protected SignInventoryDialogFragment signInventoryDialogFragment;
  private AddDrugDialogFragment addDrugDialogFragment;
  private EditDrugDialogFragment editDrugDialogFragment;
  private RemoveDrugDialogFragment removeDrugDialogFragment;
  private DeleteDrugDialogFragment deleteDrugDialogFragment;
  private DispenseDrugDialogFragment dispenseDrugDialogFragment;

  /**
   * Empty constructor is required by the Android framework.
   */
  @Inject
  public DialogService() {
  }

  /**
   * Show {@link DispenseDrugDialogFragment}
   * @param fragmentManager the fragment manager
   * @param args the constructor arguments to create the fragment
   */
  public void showDispenseDrugDialog(FragmentManager fragmentManager, DispenseDrugDialogFragmentArgs args) {
    dispenseDrugDialogFragment = dispenseDrugDialogFragmentFactory.create(args);
    if (!dispenseDrugDialogFragment.isAdded()) {
      dispenseDrugDialogFragment.show(fragmentManager, DispenseDrugDialogFragment.TAG);
    }
  }

  /**
   * Show {@link AddDrugDialogFragment}
   * @param fragmentManager the fragment manager
   * @param args the constructor arguments to create the fragment
   */
  public void showAddDrugDialog(FragmentManager fragmentManager, AddDrugDialogFragmentArgs args) {
    addDrugDialogFragment = addDrugDialogFragmentFactory.create(args);
    if (!addDrugDialogFragment.isAdded()) {
      addDrugDialogFragment.show(fragmentManager, AddDrugDialogFragment.TAG);
    }
  }

  /**
   * Show {@link EditDrugDialogFragment}
   * @param fragmentManager the fragment manager
   * @param args the constructor arguments to create the fragment
   */
  public void showEditDrugDialog(FragmentManager fragmentManager, EditDrugDialogFragmentArgs args) {
    editDrugDialogFragment = editDrugDialogFragmentFactory.create(args);
    if (!editDrugDialogFragment.isAdded()) {
      editDrugDialogFragment.show(fragmentManager, EditDrugDialogFragment.TAG);
    }
  }

  /**
   * Show {@link RemoveDrugDialogFragment}
   * @param fragmentManager the fragment manager
   * @param args the constructor arguments to create the fragment
   */
  public void showRemoveDrugDialog(FragmentManager fragmentManager, RemoveDrugDialogFragmentArgs args) {
    removeDrugDialogFragment = removeDrugDialogFragmentFactory.create(args);
    if (!removeDrugDialogFragment.isAdded()) {
      removeDrugDialogFragment.show(fragmentManager, RemoveDrugDialogFragment.TAG);
    }
  }

  /**
   * Show {@link DeleteDrugDialogFragment}
   * @param fragmentManager the fragment manager
   * @param args the constructor arguments to create the fragment
   */
  public void showDeleteDrugDialog(FragmentManager fragmentManager, DeleteDrugDialogFragmentArgs args) {
    deleteDrugDialogFragment = deleteDrugDialogFragmentFactory.create(args);
    if (!deleteDrugDialogFragment.isAdded()) {
      deleteDrugDialogFragment.show(fragmentManager, DeleteDrugDialogFragment.TAG);
    }
  }

  /**
   * Show {@link CreateDrugDialogFragment}
   * @param fragmentManager the fragment manager
   */
  public void showCreateDrugDialog(FragmentManager fragmentManager) {
    if (!createDrugDialogFragment.isAdded()) {
      createDrugDialogFragment.show(fragmentManager, CreateDrugDialogFragment.TAG);
    }
  }

  /**
   * Show {@link SignInventoryDialogFragment}
   * @param fragmentManager the fragment manager
   */
  public void showSignInventoryDialog(FragmentManager fragmentManager) {
    if (!signInventoryDialogFragment.isAdded()) {
      signInventoryDialogFragment.show(fragmentManager, SignInventoryDialogFragment.TAG);
    }
  }

  /**
   * Dismisses a dialog by it's type.
   *
   * @param dialog the dialog type to be dismissed.
   */
  public void dismiss(DialogType dialog) {
    if (dialog == DialogType.ADD_DRUG && addDrugDialogFragment.isAdded()) {
      addDrugDialogFragment.dismiss();
    } else if (dialog == DialogType.CREATE_DRUG && createDrugDialogFragment.isAdded()) {
      createDrugDialogFragment.dismiss();
    } else if (dialog == DialogType.EDIT_DRUG && editDrugDialogFragment.isAdded()) {
      editDrugDialogFragment.dismiss();
    } else if (dialog == DialogType.REMOVE_DRUG && removeDrugDialogFragment.isAdded()) {
      removeDrugDialogFragment.dismiss();
    } else if (dialog == DialogType.DELETE_DRUG && deleteDrugDialogFragment.isAdded()) {
      deleteDrugDialogFragment.dismiss();
    } else if (dialog == DialogType.DISPENSE_DRUG && dispenseDrugDialogFragment.isAdded()) {
      dispenseDrugDialogFragment.dismiss();
    } else if (dialog == DialogType.SIGN_INVENTORY && signInventoryDialogFragment.isAdded()) {
      signInventoryDialogFragment.dismiss();
    }
  }
}
