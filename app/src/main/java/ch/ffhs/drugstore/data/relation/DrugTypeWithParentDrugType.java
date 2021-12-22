package ch.ffhs.drugstore.data.relation;

import androidx.room.Embedded;
import androidx.room.Relation;

import ch.ffhs.drugstore.data.entity.DrugType;

/**
 * This class represents the recursive relation in the DrugType entity to use with Room persistence
 * library
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class DrugTypeWithParentDrugType {

    @Embedded
    public DrugType drugType;

    @Relation(parentColumn = "parentDrugTypeId", entityColumn = "drugTypeId", entity =
            DrugType.class)
    public DrugType parentDrugType;

    public DrugType getDrugType() {
        return drugType;
    }

    public DrugType getParentDrugType() {
        return parentDrugType;
    }
}
