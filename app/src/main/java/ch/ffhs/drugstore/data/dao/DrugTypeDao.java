package ch.ffhs.drugstore.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

import ch.ffhs.drugstore.data.entity.DrugType;
import ch.ffhs.drugstore.data.relation.DrugTypeWithParentDrugType;

/**
 * data access object (DAO) class abstracts access to the database for the DrugTypes objects
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
@Dao
public interface DrugTypeDao {

    @Transaction
    @Query("SELECT * FROM drugTypes")
    LiveData<List<DrugTypeWithParentDrugType>> getAllDrugTypes();

    @Insert
    long insert(DrugType drugType);
}
