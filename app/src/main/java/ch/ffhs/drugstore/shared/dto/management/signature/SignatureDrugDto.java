package ch.ffhs.drugstore.shared.dto.management.signature;

import ch.ffhs.drugstore.shared.dto.management.drugs.DrugDto;

/**
 * DTO to respresent a signature drug
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class SignatureDrugDto {
    private int signatureId;
    private int drugId;
    private DrugDto drug;
    private double expectedAmount;
    private double actualAmount;
    private boolean approved;

    public SignatureDrugDto(
            int signatureId,
            int drugId,
            DrugDto drug,
            double expectedAmount,
            double actualAmount,
            boolean approved) {
        this.signatureId = signatureId;
        this.drugId = drugId;
        this.drug = drug;
        this.expectedAmount = expectedAmount;
        this.actualAmount = actualAmount;
        this.approved = approved;
    }

    public int getSignatureId() {
        return signatureId;
    }

    public void setSignatureId(int signatureId) {
        this.signatureId = signatureId;
    }

    public int getDrugId() {
        return drugId;
    }

    public void setDrugId(int drugId) {
        this.drugId = drugId;
    }

    public DrugDto getDrug() {
        return drug;
    }

    public void setDrug(DrugDto drug) {
        this.drug = drug;
    }

    public double getExpectedAmount() {
        return expectedAmount;
    }

    public void setExpectedAmount(double expectedAmount) {
        this.expectedAmount = expectedAmount;
    }

    public double getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(double actualAmount) {
        this.actualAmount = actualAmount;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}
