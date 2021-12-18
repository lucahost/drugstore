package ch.ffhs.drugstore.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

import ch.ffhs.drugstore.data.entity.SignatureDrug;
import ch.ffhs.drugstore.data.relation.SignatureDrugWithDrug;

/**
 * data access object (DAO) class abstracts access to the database for the SignatureDrug objects
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
@Dao
public interface SignatureDrugDao {
    /**
     * @Insert
     * @Update
     * @Delete insert, update and delete rows without SQL code because of room library
     */
    @Insert
    void insert(SignatureDrug... signatureDrug);

    @Update
    void update(SignatureDrug signatureDrug);

    @Delete
    void delete(SignatureDrug signatureDrug);

    /**
     * @Query methods for special queries
     */
    @Query("DELETE FROM signatureDrugs")
    void deleteAll();

    /**
     * @Transaction methods for relations (join)
     */
    @Transaction
    @Query("SELECT * FROM signatureDrugs")
    LiveData<List<SignatureDrug>> getAllSignatureDrugs();

    @Transaction
    @Query("SELECT * FROM signatureDrugs WHERE signatureId = :signatureId")
    LiveData<List<SignatureDrug>> getSignatureDrugsBySignatureId(int signatureId);

    @Transaction
    @Query("SELECT * FROM signatureDrugs WHERE signatureId = :signatureId")
    LiveData<List<SignatureDrugWithDrug>> getSignatureDrugsWithDrugBySignatureId(int signatureId);
}
