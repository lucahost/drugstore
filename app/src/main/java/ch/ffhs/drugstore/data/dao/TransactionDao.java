package ch.ffhs.drugstore.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import ch.ffhs.drugstore.data.entity.Transaction;
import ch.ffhs.drugstore.data.relation.TransactionWithDrugAndUser;

/**
 * data access object (DAO) class abstracts access to the database for the Transaction objects
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
@Dao
public interface TransactionDao {

    @Insert
    void insert(Transaction transaction);

    @Update
    void update(Transaction transaction);

    @Delete
    void delete(Transaction transaction);

    @Query("DELETE FROM transactions")
    void deleteAll();

    @androidx.room.Transaction
    @Query("SELECT * FROM transactions ORDER BY createdAt DESC")
    LiveData<List<TransactionWithDrugAndUser>> getAllTransactions();
}
