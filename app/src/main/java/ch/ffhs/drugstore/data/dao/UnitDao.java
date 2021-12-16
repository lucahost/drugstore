package ch.ffhs.drugstore.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import ch.ffhs.drugstore.data.entity.Unit;
import ch.ffhs.drugstore.shared.dto.management.drugs.UnitDto;


@Dao
public interface UnitDao {
    @Query("SELECT unitId, title FROM units")
    LiveData<List<UnitDto>> getAllUnits();

    @Insert
    long insert(Unit unit);
}
