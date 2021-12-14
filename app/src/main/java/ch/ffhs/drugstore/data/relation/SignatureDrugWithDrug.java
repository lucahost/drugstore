package ch.ffhs.drugstore.data.relation;

import androidx.room.Embedded;
import androidx.room.Relation;

import ch.ffhs.drugstore.data.entity.Drug;
import ch.ffhs.drugstore.data.entity.SignatureDrug;

public class SignatureDrugWithDrug {
    @Embedded
    public SignatureDrug signatureDrug;

    @Relation(
            parentColumn = "drugId",
            entityColumn = "drugId",
            entity = Drug.class
    )
    public Drug drug;
}
