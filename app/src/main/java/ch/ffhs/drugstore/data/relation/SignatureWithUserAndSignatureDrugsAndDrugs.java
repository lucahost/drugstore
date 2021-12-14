package ch.ffhs.drugstore.data.relation;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

import ch.ffhs.drugstore.data.entity.Signature;
import ch.ffhs.drugstore.data.entity.SignatureDrug;

public class SignatureWithSignatureDrugsAndDrugs {
    @Embedded
    public Signature signature;

    @Relation(
            parentColumn = "signatureId",
            entityColumn = "signatureId",
            entity = SignatureDrug.class
    )
    public List<SignatureDrugWithDrug> signatureDrugsWithDrugs;
}
