package ch.ffhs.drugstore.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.RewriteQueriesToDropUnusedColumns;
import androidx.room.RoomWarnings;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

import ch.ffhs.drugstore.data.dto.SignatureDto;
import ch.ffhs.drugstore.data.dto.SignatureWithDrugs;
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
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM signatures")
    LiveData<List<SignatureDto>> getSignatures();

    @RewriteQueriesToDropUnusedColumns
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Transaction
    @Query("SELECT * FROM signatures " +
            "JOIN users on signatures.userId = users.userId "
    )
    LiveData<List<SignatureWithDrugs>> getAllSignatures();
}
