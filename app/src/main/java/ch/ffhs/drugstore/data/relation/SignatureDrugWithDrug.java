package ch.ffhs.drugstore.data.relation;

import androidx.room.Embedded;
import androidx.room.Relation;

import ch.ffhs.drugstore.data.entity.Drug;
import ch.ffhs.drugstore.data.entity.SignatureDrug;

/**
 * This class represents the relation between the entity SignatureDrug and Drug to use with Room
 * persistence library
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class SignatureDrugWithDrug {
    /**
     * @Embedded allows to represent two entities as one
     * @Relation represents join between two entities
     */
    @Embedded
    public SignatureDrug signatureDrug;

    @Relation(
            parentColumn = "drugId",
            entityColumn = "drugId",
            entity = Drug.class
    )
    public DrugWithUnitAndDrugTypeAndSubstance drug;
}
