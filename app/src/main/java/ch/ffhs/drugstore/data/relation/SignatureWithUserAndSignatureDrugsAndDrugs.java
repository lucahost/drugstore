package ch.ffhs.drugstore.data.relation;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

import ch.ffhs.drugstore.data.entity.Signature;
import ch.ffhs.drugstore.data.entity.SignatureDrug;
import ch.ffhs.drugstore.data.entity.User;

/**
 * This class represents the relation between the entity Signature with User
 * and Signature Drugs and Drugs
 * to use with Room persistence library
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class SignatureWithUserAndSignatureDrugsAndDrugs {
    /**
     * @Embedded    allows to represent two entities as one
     * @Relation    represents join between two entities
     * @Junction    use relation with junction table
     */
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
