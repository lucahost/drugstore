package ch.ffhs.drugstore.data.relation;

import androidx.room.Embedded;
import androidx.room.Relation;

import ch.ffhs.drugstore.data.entity.Drug;
import ch.ffhs.drugstore.data.entity.Transaction;
import ch.ffhs.drugstore.data.entity.User;

/**
 * This class represents the relation between the entity Transaction with Drug and User
 * to use with Room persistence library
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class TransactionWithDrugAndUser {
    /**
     * @Embedded    allows to represent two entities as one
     * @Relation    represents join between two entities
     */
    @Embedded
    public Transaction transaction;

    @Relation(
            parentColumn = "drugId",
            entityColumn = "drugId",
            entity = Drug.class
    )
    public DrugWithUnitAndDrugTypeAndSubstance drug;

    /**
     * second join
     */
    @Relation(
            parentColumn = "userId",
            entityColumn = "userId",
            entity = User.class
    )
    public User user;

    public Transaction getTransaction() {
        return transaction;
    }

    public DrugWithUnitAndDrugTypeAndSubstance getDrug() {
        return drug;
    }

    public User getUser() {
        return user;
    }
}
