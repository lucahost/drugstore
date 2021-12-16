package ch.ffhs.drugstore.data.relation;

import androidx.room.Embedded;
import androidx.room.Relation;

import ch.ffhs.drugstore.data.entity.Drug;
import ch.ffhs.drugstore.data.entity.Transaction;
import ch.ffhs.drugstore.data.entity.User;

public class TransactionWithDrugAndUser {
    @Embedded
    public Transaction transaction;

    @Relation(
            parentColumn = "drugId",
            entityColumn = "drugId",
            entity = Drug.class
    )
    public Drug drug;

    @Relation(
            parentColumn = "userId",
            entityColumn = "userId",
            entity = User.class
    )
    public User user;

    public Transaction getTransaction() {
        return transaction;
    }

    public Drug getDrug() {
        return drug;
    }

    public User getUser() {
        return user;
    }
}
