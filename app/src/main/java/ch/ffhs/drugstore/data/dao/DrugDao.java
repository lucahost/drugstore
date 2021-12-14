package ch.ffhs.drugstore.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.RewriteQueriesToDropUnusedColumns;
import androidx.room.RoomWarnings;
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
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT *, drugTypes.title as drugType, substances.title as substance FROM drugs " +
            "LEFT JOIN drugTypes on drugs.drugTypeId = drugTypes.drugTypeId " +
            "LEFT JOIN substances on drugs.substanceId = substances.substanceId")
    LiveData<List<DrugDto>> getAllDrugs();

    @RewriteQueriesToDropUnusedColumns
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT *, drugTypes.title as drugType, substances.title as substance FROM drugs " +
            "LEFT JOIN drugTypes on drugs.drugTypeId = drugTypes.drugTypeId " +
            "LEFT JOIN substances on drugs.substanceId = substances.substanceId " +
            "WHERE stockAmount > 0 AND drugs.title LIKE '%' || :searchTerm || '%'"
    )
    LiveData<List<DrugDto>> getOnStockDrugs(String searchTerm);

    @RewriteQueriesToDropUnusedColumns
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT *, drugTypes.title as drugType, substances.title as substance FROM drugs " +
            "LEFT JOIN drugTypes on drugs.drugTypeId = drugTypes.drugTypeId " +
            "LEFT JOIN substances on drugs.substanceId = substances.substanceId " +
            "WHERE stockAmount > 0 AND isFavorite = 1 AND drugs.title LIKE '%' || :searchTerm || '%'"
    )
    LiveData<List<DrugDto>> getOnStockFavoriteDrugs(String searchTerm);

    @RewriteQueriesToDropUnusedColumns
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT *, drugTypes.title as drugType, substances.title as substance FROM drugs " +
            "LEFT JOIN drugTypes on drugs.drugTypeId = drugTypes.drugTypeId " +
            "LEFT JOIN substances on drugs.substanceId = substances.substanceId " +
            "WHERE stockAmount > 0 AND drugTypes.drugTypeId IN (:drugTypeIds) AND drugs.title LIKE '%' || :searchTerm || '%'"
    )
    LiveData<List<DrugDto>> getOnStockDrugsByDrugTypes(List<Integer> drugTypeIds, String searchTerm);

    @RewriteQueriesToDropUnusedColumns
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT *, drugTypes.title as drugType, substances.title as substance FROM drugs " +
            "LEFT JOIN drugTypes on drugs.drugTypeId = drugTypes.drugTypeId " +
            "LEFT JOIN substances on drugs.substanceId = substances.substanceId " +
            "WHERE stockAmount > 0 AND isFavorite = 1 AND drugTypes.drugTypeId IN (:drugTypeIds) AND drugs.title LIKE '%' || :searchTerm || '%'"
    )
    LiveData<List<DrugDto>> getOnStockFavoriteDrugsByDrugTypes(List<Integer> drugTypeIds, String searchTerm);
}
