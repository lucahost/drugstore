package ch.ffhs.drugstore.presentation;

import androidx.fragment.app.FragmentManager;

import javax.inject.Inject;

import ch.ffhs.drugstore.presentation.dispensary.view.dialog.DispenseDrugDialogFragment;
import ch.ffhs.drugstore.presentation.management.drugs.view.dialog.AddDrugDialogFragment;
import ch.ffhs.drugstore.presentation.management.drugs.view.dialog.CreateDrugDialogFragment;
import ch.ffhs.drugstore.presentation.management.drugs.view.dialog.DeleteDrugDialogFragment;
import ch.ffhs.drugstore.presentation.management.drugs.view.dialog.EditDrugDialogFragment;
import ch.ffhs.drugstore.presentation.management.drugs.view.dialog.RemoveDrugDialogFragment;
import ch.ffhs.drugstore.presentation.management.inventory.view.dialog.SignInventoryDialogFragment;

public class DialogService {

  @Inject AddDrugDialogFragment addDrugDialogFragment;
  @Inject CreateDrugDialogFragment createDrugDialogFragment;
  @Inject EditDrugDialogFragment editDrugDialogFragment;
  @Inject RemoveDrugDialogFragment removeDrugDialogFragment;
  @Inject DeleteDrugDialogFragment deleteDrugDialogFragment;
  @Inject SignInventoryDialogFragment signInventoryDialogFragment;
  @Inject DispenseDrugDialogFragment dispenseDrugDialogFragment;

  @Inject
  public DialogService() {
  }

  public void show(FragmentManager fragmentManager, Dialog dialog) {
    if (dialog == Dialog.ADD_DRUG && !addDrugDialogFragment.isAdded()) {
      addDrugDialogFragment.show(fragmentManager, AddDrugDialogFragment.TAG);
    } else if (dialog == Dialog.CREATE_DRUG && !createDrugDialogFragment.isAdded()) {
      createDrugDialogFragment.show(fragmentManager, CreateDrugDialogFragment.TAG);
    } else if (dialog == Dialog.EDIT_DRUG && !editDrugDialogFragment.isAdded()) {
      editDrugDialogFragment.show(fragmentManager, EditDrugDialogFragment.TAG);
    } else if (dialog == Dialog.REMOVE_DRUG && !removeDrugDialogFragment.isAdded()) {
      removeDrugDialogFragment.show(fragmentManager, RemoveDrugDialogFragment.TAG);
    } else if (dialog == Dialog.DELETE_DRUG && !deleteDrugDialogFragment.isAdded()) {
      deleteDrugDialogFragment.show(fragmentManager, DeleteDrugDialogFragment.TAG);
    } else if (dialog == Dialog.DISPENSE_DRUG && !dispenseDrugDialogFragment.isAdded()) {
      dispenseDrugDialogFragment.show(fragmentManager, DispenseDrugDialogFragment.TAG);
    } else if (dialog == Dialog.SIGN_INVENTORY && !signInventoryDialogFragment.isAdded()) {
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
