package ch.ffhs.drugstore.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import ch.ffhs.drugstore.data.dto.UnitDto;


@Dao
public interface UnitDao {
    @Query("SELECT unitId, title FROM units")
    LiveData<List<UnitDto>> getAllUnits();
}
