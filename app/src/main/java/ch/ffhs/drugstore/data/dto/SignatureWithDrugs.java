package ch.ffhs.drugstore.data.dto;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

import ch.ffhs.drugstore.data.entity.Signature;
import ch.ffhs.drugstore.data.entity.SignatureDrug;

public class SignatureWithDrugs {
    @Embedded
    public Signature signature;

    @Relation(
            parentColumn = "signatureId",
            entityColumn = "signatureId",
            associateBy = @Junction(SignatureDrug.class)
    )
    public List<SignatureDrug> signatureDrugs;
}
