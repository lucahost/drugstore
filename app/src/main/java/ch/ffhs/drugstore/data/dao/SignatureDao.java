package ch.ffhs.drugstore.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.RewriteQueriesToDropUnusedColumns;
import androidx.room.Update;

import java.util.List;

import ch.ffhs.drugstore.data.dto.SignatureDto;
import ch.ffhs.drugstore.data.entity.Signature;

@Dao
public interface SignatureDao {

    @Insert
    void insert(Signature signature);

    @Update
    void update(Signature signature);

    @Delete
    void delete(Signature signature);

    @Query("DELETE FROM signatures")
    void deleteAll();

    @RewriteQueriesToDropUnusedColumns
    @Query("SELECT * FROM signatures " +
            "LEFT JOIN signatureDrugs on signatures.signatureId = signatureDrugs.signatureId " +
            "JOIN drugs on signatureDrugs.drugId = drugs.drugId " +
            "JOIN users on signatures.userId = users.userId "
    )
    LiveData<List<SignatureDto>> getAllSignatures();
}
