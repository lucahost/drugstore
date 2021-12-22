package ch.ffhs.drugstore.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import ch.ffhs.drugstore.data.entity.Unit;
import ch.ffhs.drugstore.shared.dto.management.drugs.UnitDto;

/**
 * data access object (DAO) class abstracts access to the database for the Unit objects
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
@Dao
public interface UnitDao {

    @Query("SELECT unitId, title FROM units")
    LiveData<List<UnitDto>> getAllUnits();

    @Insert
    long insert(Unit unit);
}
