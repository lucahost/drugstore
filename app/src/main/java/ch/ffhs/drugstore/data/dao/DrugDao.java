package ch.ffhs.drugstore.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.RewriteQueriesToDropUnusedColumns;
import androidx.room.Update;

import java.util.List;

import ch.ffhs.drugstore.data.dto.DrugDto;
import ch.ffhs.drugstore.data.entity.Drug;


@Dao
public interface DrugDao {

    @Insert
    void insert(Drug drug);

    @Update
    void update(Drug drug);

    @Delete
    void delete(Drug drug);

    @Query("DELETE FROM drugs")
    void deleteAll();

    @RewriteQueriesToDropUnusedColumns
    @Query("SELECT *, drugTypes.title as drugType, substances.title as substance FROM drugs " +
            "JOIN drugTypes on drugs.drugTypeId = drugTypes.drugTypeId " +
            "JOIN substances on drugs.substanceId = substances.substanceId")
    LiveData<List<DrugDto>> getAllDrugs();

    @RewriteQueriesToDropUnusedColumns
    @Query("SELECT *, drugTypes.title as drugType, substances.title as substance FROM drugs " +
            "JOIN drugTypes on drugs.drugTypeId = drugTypes.drugTypeId " +
            "JOIN substances on drugs.substanceId = substances.substanceId " +
            "WHERE stockAmount > 0"
    )
    LiveData<List<DrugDto>> getOnStockDrugs();

}
