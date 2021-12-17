package ch.ffhs.drugstore.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

import ch.ffhs.drugstore.data.entity.Drug;
import ch.ffhs.drugstore.data.relation.DrugWithUnitAndDrugTypeAndSubstance;

/**
 * data access object (DAO) class
 * abstracts access to the database for the Drug objects
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
@Dao
public interface DrugDao {
    /**
     * @Insert
     * @Update
     * @Delete
     * insert, update and delete rows without SQL code (room library)
     */
    @Insert
    void insert(Drug drug);

    @Update
    void update(Drug drug);

    @Delete
    void delete(Drug drug);

    /**
     * @Query   methods for special queries
     */
    @Query("DELETE FROM drugs")
    void deleteAll();

    @Query("DELETE FROM drugs WHERE drugId = :drugId")
    void deleteDrugById(int drugId);

    /**
     * @Transaction methods for relations (join)
     */
    @Transaction
    @Query("SELECT * FROM drugs WHERE drugId = :drugId")
    DrugWithUnitAndDrugTypeAndSubstance getDrugById(int drugId);

    @Transaction
    @Query("SELECT * FROM drugs")
    LiveData<List<DrugWithUnitAndDrugTypeAndSubstance>> getAllDrugs();

    @Transaction
    @Query("SELECT * FROM drugs " +
            "WHERE stockAmount > 0 AND drugs.title LIKE '%' || :searchTerm || '%'"
    )
    LiveData<List<DrugWithUnitAndDrugTypeAndSubstance>> getOnStockDrugs(String searchTerm);

    @Transaction
    @Query("SELECT * FROM drugs " +
            "WHERE stockAmount > 0 AND isFavorite = 1 AND drugs.title LIKE '%' || :searchTerm || "
            + "'%'"
    )
    LiveData<List<DrugWithUnitAndDrugTypeAndSubstance>> getOnStockFavoriteDrugs(String searchTerm);

    @Transaction
    @Query("SELECT * FROM drugs " +
            "WHERE stockAmount > 0 AND drugs.drugTypeId IN (:drugTypeIds) AND drugs.title LIKE '%' " +
            "|| :searchTerm || '%'"
    )
    LiveData<List<DrugWithUnitAndDrugTypeAndSubstance>> getOnStockDrugsByDrugTypes(
            List<Integer> drugTypeIds, String searchTerm);

    @Transaction
    @Query("SELECT * FROM drugs " +
            "WHERE stockAmount > 0 AND isFavorite = 1 AND drugs.drugTypeId IN (:drugTypes) AND " +
            "drugs.title LIKE '%' || :searchTerm || '%'"
    )
    LiveData<List<DrugWithUnitAndDrugTypeAndSubstance>> getOnStockFavoriteDrugsByDrugTypes(
            List<Integer> drugTypes,
            String searchTerm);

    @Transaction
    @Query("UPDATE drugs SET isFavorite = ((isFavorite | 1) - (isFavorite & 1)) WHERE drugId = :drugId")
    void toggleDrugIsFavorite(int drugId);
}
