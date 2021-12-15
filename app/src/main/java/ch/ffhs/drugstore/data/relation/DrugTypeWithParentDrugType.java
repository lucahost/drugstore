package ch.ffhs.drugstore.data.relation;

import androidx.room.Embedded;
import androidx.room.Relation;

import ch.ffhs.drugstore.data.entity.DrugType;

public class DrugTypeWithParentDrugType {
    @Embedded
    public DrugType drugType;

    @Relation(
            parentColumn = "parentDrugTypeId",
            entityColumn = "drugTypeId",
            entity = DrugType.class
    )
    public DrugType parentDrugType;

    public DrugType getDrugType() {
        return drugType;
    }

    public DrugType getParentDrugType() {
        return parentDrugType;
    }
}
