package ch.ffhs.drugstore.data.entity;

public class InventoryDrug {
  public int inventoryDrugId;
  public int drugId;
  public long amount;
  public boolean approved;

  public InventoryDrug(int inventoryDrugId, int drugId, long amount, boolean approved) {
    this.inventoryDrugId = inventoryDrugId;
    this.drugId = drugId;
    this.amount = amount;
    this.approved = approved;
  }

  public int getInventoryDrugId() {
    return inventoryDrugId;
  }

  public void setInventoryDrugId(int inventoryDrugId) {
    this.inventoryDrugId = inventoryDrugId;
  }

  public int getDrugId() {
    return drugId;
  }

  public void setDrugId(int drugId) {
    this.drugId = drugId;
  }

  public long getAmount() {
    return amount;
  }

  public void setAmount(long amount) {
    this.amount = amount;
  }

  public boolean isApproved() {
    return approved;
  }

  public void setApproved(boolean approved) {
    this.approved = approved;
  }
}
