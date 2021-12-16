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

@Dao
public interface SignatureDrugDao {
    @Insert
    void insert(SignatureDrug... signatureDrug);

    @Update
    void update(SignatureDrug signatureDrug);

    @Delete
    void delete(SignatureDrug signatureDrug);

    @Query("DELETE FROM signatureDrugs")
    void deleteAll();

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
