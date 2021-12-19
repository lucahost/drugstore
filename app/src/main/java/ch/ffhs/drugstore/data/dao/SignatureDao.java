package ch.ffhs.drugstore.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

import ch.ffhs.drugstore.data.entity.Signature;
import ch.ffhs.drugstore.data.relation.SignatureWithUserAndSignatureDrugsAndDrugs;

/**
 * data access object (DAO) class abstracts access to the database for the Signature objects
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
@Dao
public interface SignatureDao {
    /**
     * @Insert
     * @Update
     * @Delete insert, update and delete rows without SQL code (room library)
     */
    @Insert
    long insert(Signature signature);

    @Update
    void update(Signature signature);

    @Delete
    void delete(Signature signature);

    /**
     * @Query methods for special queries
     */
    @Query("DELETE FROM signatures")
    void deleteAll();

    /**
     * @Transaction methods for relations (join)
     */
    @Transaction
    @Query("SELECT * FROM signatures ORDER BY createdAt DESC")
    LiveData<List<SignatureWithUserAndSignatureDrugsAndDrugs>> getAllSignatures();

    @Transaction
    @Query("SELECT * FROM signatures WHERE signatureId = :signatureId")
    SignatureWithUserAndSignatureDrugsAndDrugs getSignatureBySignatureId(int signatureId);
}
