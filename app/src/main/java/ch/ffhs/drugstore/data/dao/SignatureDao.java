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

@Dao
public interface SignatureDao {

    @Insert
    long insert(Signature signature);

    @Update
    void update(Signature signature);

    @Delete
    void delete(Signature signature);

    @Query("DELETE FROM signatures")
    void deleteAll();

    @Transaction
    @Query("SELECT * FROM signatures ORDER BY createdAt DESC")
    LiveData<List<SignatureWithUserAndSignatureDrugsAndDrugs>> getAllSignatures();

    @Transaction
    @Query("SELECT * FROM signatures WHERE signatureId = :signatureId")
    SignatureWithUserAndSignatureDrugsAndDrugs getSignatureBySignatureId(int signatureId);
}
