package ch.ffhs.drugstore.presentation;

import androidx.fragment.app.FragmentManager;

import javax.inject.Inject;

import ch.ffhs.drugstore.presentation.dispensary.view.dialog.DispenseDrugDialogFragment;
import ch.ffhs.drugstore.presentation.dispensary.view.dialog.DispenseDrugDialogFragmentArgs;
import ch.ffhs.drugstore.presentation.dispensary.view.dialog.DispenseDrugDialogFragmentFactory;
import ch.ffhs.drugstore.presentation.management.drugs.view.dialog.AddDrugDialogFragment;
import ch.ffhs.drugstore.presentation.management.drugs.view.dialog.AddDrugDialogFragmentArgs;
import ch.ffhs.drugstore.presentation.management.drugs.view.dialog.AddDrugDialogFragmentFactory;
import ch.ffhs.drugstore.presentation.management.drugs.view.dialog.CreateDrugDialogFragment;
import ch.ffhs.drugstore.presentation.management.drugs.view.dialog.DeleteDrugDialogFragment;
import ch.ffhs.drugstore.presentation.management.drugs.view.dialog.DeleteDrugDialogFragmentArgs;
import ch.ffhs.drugstore.presentation.management.drugs.view.dialog.DeleteDrugDialogFragmentFactory;
import ch.ffhs.drugstore.presentation.management.drugs.view.dialog.EditDrugDialogFragment;
import ch.ffhs.drugstore.presentation.management.drugs.view.dialog.EditDrugDialogFragmentArgs;
import ch.ffhs.drugstore.presentation.management.drugs.view.dialog.EditDrugDialogFragmentFactory;
import ch.ffhs.drugstore.presentation.management.drugs.view.dialog.RemoveDrugDialogFragment;
import ch.ffhs.drugstore.presentation.management.drugs.view.dialog.RemoveDrugDialogFragmentArgs;
import ch.ffhs.drugstore.presentation.management.drugs.view.dialog.RemoveDrugDialogFragmentFactory;
import ch.ffhs.drugstore.presentation.management.inventory.view.dialog.SignInventoryDialogFragment;

public class DialogService {

  @Inject AddDrugDialogFragmentFactory addDrugDialogFragmentFactory;
  @Inject DeleteDrugDialogFragmentFactory deleteDrugDialogFragmentFactory;
  @Inject EditDrugDialogFragmentFactory editDrugDialogFragmentFactory;
  @Inject RemoveDrugDialogFragmentFactory removeDrugDialogFragmentFactory;
  @Inject DispenseDrugDialogFragmentFactory dispenseDrugDialogFragmentFactory;
  @Inject CreateDrugDialogFragment createDrugDialogFragment;
  @Inject SignInventoryDialogFragment signInventoryDialogFragment;
  AddDrugDialogFragment addDrugDialogFragment;
  EditDrugDialogFragment editDrugDialogFragment;
  RemoveDrugDialogFragment removeDrugDialogFragment;
  DeleteDrugDialogFragment deleteDrugDialogFragment;
  DispenseDrugDialogFragment dispenseDrugDialogFragment;

  @Inject
  public DialogService() {
  }

  public void showDispenseDrugDialog(FragmentManager fragmentManager, DispenseDrugDialogFragmentArgs args) {

    dispenseDrugDialogFragment = dispenseDrugDialogFragmentFactory.create(args);
    if (!dispenseDrugDialogFragment.isAdded()) {
      dispenseDrugDialogFragment.show(fragmentManager, DispenseDrugDialogFragment.TAG);
    }
  }

  public void showAddDrugDialog(FragmentManager fragmentManager, AddDrugDialogFragmentArgs args) {

    addDrugDialogFragment = addDrugDialogFragmentFactory.create(args);
    if (!addDrugDialogFragment.isAdded()) {
      addDrugDialogFragment.show(fragmentManager, AddDrugDialogFragment.TAG);
    }
  }

  public void showEditDrugDialog(FragmentManager fragmentManager, EditDrugDialogFragmentArgs args) {

    editDrugDialogFragment = editDrugDialogFragmentFactory.create(args);
    if (!editDrugDialogFragment.isAdded()) {
      editDrugDialogFragment.show(fragmentManager, EditDrugDialogFragment.TAG);
    }
  }

  public void showRemoveDrugDialog(FragmentManager fragmentManager, RemoveDrugDialogFragmentArgs args) {

    removeDrugDialogFragment = removeDrugDialogFragmentFactory.create(args);
    if (!removeDrugDialogFragment.isAdded()) {
      removeDrugDialogFragment.show(fragmentManager, RemoveDrugDialogFragment.TAG);
    }
  }

  public void showDeleteDrugDialog(FragmentManager fragmentManager, DeleteDrugDialogFragmentArgs args) {
    deleteDrugDialogFragment = deleteDrugDialogFragmentFactory.create(args);
    if (!deleteDrugDialogFragment.isAdded()) {
      deleteDrugDialogFragment.show(fragmentManager, DeleteDrugDialogFragment.TAG);
    }
  }

  public void showCreateDrugDialog(FragmentManager fragmentManager) {
    if (!createDrugDialogFragment.isAdded()) {
      createDrugDialogFragment.show(fragmentManager, CreateDrugDialogFragment.TAG);
    }
  }

  public void showSignInventoryDialog(FragmentManager fragmentManager) {
    if (!signInventoryDialogFragment.isAdded()) {
      signInventoryDialogFragment.show(fragmentManager, SignInventoryDialogFragment.TAG);
    }
  }

  public void dismiss(Dialog dialog) {
    if (dialog == Dialog.ADD_DRUG && addDrugDialogFragment.isAdded()) {
      addDrugDialogFragment.dismiss();
    } else if (dialog == Dialog.CREATE_DRUG && createDrugDialogFragment.isAdded()) {
      createDrugDialogFragment.dismiss();
    } else if (dialog == Dialog.EDIT_DRUG && editDrugDialogFragment.isAdded()) {
      editDrugDialogFragment.dismiss();
    } else if (dialog == Dialog.REMOVE_DRUG && removeDrugDialogFragment.isAdded()) {
      removeDrugDialogFragment.dismiss();
    } else if (dialog == Dialog.DELETE_DRUG && deleteDrugDialogFragment.isAdded()) {
      deleteDrugDialogFragment.dismiss();
    } else if (dialog == Dialog.DISPENSE_DRUG && dispenseDrugDialogFragment.isAdded()) {
      dispenseDrugDialogFragment.dismiss();
    } else if (dialog == Dialog.SIGN_INVENTORY && signInventoryDialogFragment.isAdded()) {
      signInventoryDialogFragment.dismiss();
    }
  }

  public enum Dialog {
    ADD_DRUG,
    CREATE_DRUG,
    EDIT_DRUG,
    REMOVE_DRUG,
    DELETE_DRUG,
    DISPENSE_DRUG,
    SIGN_INVENTORY
  }
}
