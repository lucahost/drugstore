package ch.ffhs.drugstore.data.relation;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

import ch.ffhs.drugstore.data.entity.Signature;
import ch.ffhs.drugstore.data.entity.SignatureDrug;
import ch.ffhs.drugstore.data.entity.User;

public class SignatureWithUserAndSignatureDrugsAndDrugs {
    @Embedded
    public Signature signature;

    @Relation(
            parentColumn = "userId",
            entityColumn = "userId",
            entity = User.class
    )
    public User user;

    @Relation(
            parentColumn = "signatureId",
            entityColumn = "drugId",
            associateBy = @Junction(SignatureDrug.class)
    )
    public List<SignatureDrug> signatureDrugs;
}
