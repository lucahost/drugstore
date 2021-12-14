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

import ch.ffhs.drugstore.data.entity.Drug;
import ch.ffhs.drugstore.data.relation.DrugWithUnitAndDrugTypeAndSubstance;
import ch.ffhs.drugstore.shared.dto.management.drugs.DrugDto;


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

    @Transaction
    @Query("SELECT * FROM drugs WHERE drugId = :drugId")
    DrugWithUnitAndDrugTypeAndSubstance getDrugById(int drugId);

    @Transaction
    @Query("SELECT * FROM drugs")
    LiveData<List<DrugWithUnitAndDrugTypeAndSubstance>> getAllDrugs();

    @RewriteQueriesToDropUnusedColumns
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT *, drugTypes.title as drugType, substances.title as substance FROM drugs " +
            "LEFT JOIN drugTypes on drugs.drugTypeId = drugTypes.drugTypeId " +
            "LEFT JOIN substances on drugs.substanceId = substances.substanceId " +
            "LEFT JOIN units on drugs.unitId = units.unitId " +
            "WHERE stockAmount > 0 AND drugs.title LIKE '%' || :searchTerm || '%'"
    )
    LiveData<List<DrugDto>> getOnStockDrugs(String searchTerm);

    @RewriteQueriesToDropUnusedColumns
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT *, drugTypes.title as drugType, substances.title as substance FROM drugs " +
            "LEFT JOIN drugTypes on drugs.drugTypeId = drugTypes.drugTypeId " +
            "LEFT JOIN substances on drugs.substanceId = substances.substanceId " +
            "LEFT JOIN units on drugs.unitId = units.unitId " +
            "WHERE stockAmount > 0 AND isFavorite = 1 AND drugs.title LIKE '%' || :searchTerm || "
            + "'%'"
    )
    LiveData<List<DrugDto>> getOnStockFavoriteDrugs(String searchTerm);

    @RewriteQueriesToDropUnusedColumns
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT *, drugTypes.title as drugType, substances.title as substance FROM drugs " +
            "LEFT JOIN drugTypes on drugs.drugTypeId = drugTypes.drugTypeId " +
            "LEFT JOIN substances on drugs.substanceId = substances.substanceId " +
            "LEFT JOIN units on drugs.unitId = units.unitId " +
            "WHERE stockAmount > 0 AND drugTypes.title IN (:drugTypeIds) AND drugs.title LIKE '%' " +
            "|| :searchTerm || '%'"
    )
    LiveData<List<DrugDto>> getOnStockDrugsByDrugTypes(List<Integer> drugTypeIds, String searchTerm);

    @RewriteQueriesToDropUnusedColumns
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT *, drugTypes.title as drugType, substances.title as substance FROM drugs " +
            "LEFT JOIN drugTypes on drugs.drugTypeId = drugTypes.drugTypeId " +
            "LEFT JOIN substances on drugs.substanceId = substances.substanceId " +
            "LEFT JOIN units on drugs.unitId = units.unitId " +
            "WHERE stockAmount > 0 AND isFavorite = 1 AND drugTypes.drugTypeId IN (:drugTypes) AND " +
            "drugs.title LIKE '%' || :searchTerm || '%'"
    )
    LiveData<List<DrugDto>> getOnStockFavoriteDrugsByDrugTypes(List<Integer> drugTypes,
            String searchTerm);
}
