package ch.ffhs.drugstore.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

import ch.ffhs.drugstore.data.relation.DrugTypeWithParentDrugType;


@Dao
public interface DrugTypeDao {
    @Transaction
    @Query("SELECT * FROM drugTypes")
    LiveData<List<DrugTypeWithParentDrugType>> getAllDrugTypes();
}
