package ch.ffhs.drugstore.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import ch.ffhs.drugstore.data.dto.DrugTypeDto;


@Dao
public interface DrugTypeDao {
    @Query("SELECT drugTypeId, title FROM drugTypes")
    LiveData<List<DrugTypeDto>> getAllDrugTypes();
}
