package ch.ffhs.drugstore.data.relation;

import androidx.room.Embedded;
import androidx.room.Relation;

import ch.ffhs.drugstore.data.entity.Drug;
import ch.ffhs.drugstore.data.entity.DrugType;
import ch.ffhs.drugstore.data.entity.Substance;
import ch.ffhs.drugstore.data.entity.Unit;

/**
 * This class represents the relation between the entities Drug with Unit and DrugType and Substance
 * to use with Room persistence library
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class DrugWithUnitAndDrugTypeAndSubstance {

    @Embedded
    public Drug drug;

    @Relation(parentColumn = "unitId", entityColumn = "unitId", entity = Unit.class)
    public Unit unit;

    /**
     * second join
     */
    @Relation(parentColumn = "drugTypeId", entityColumn = "drugTypeId", entity = DrugType.class)
    public DrugType drugType;

    /**
     * third join
     */
    @Relation(parentColumn = "substanceId", entityColumn = "substanceId", entity = Substance.class)
    public Substance substance;

    public Drug getDrug() {
        return drug;
    }

    public Unit getUnit() {
        return unit;
    }

    public DrugType getDrugType() {
        return drugType;
    }

    public Substance getSubstance() {
        return substance;
    }
}
