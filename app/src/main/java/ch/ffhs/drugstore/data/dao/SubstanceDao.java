package ch.ffhs.drugstore.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import ch.ffhs.drugstore.data.entity.Substance;

/**
 * data access object (DAO) class abstracts access to the database for the Substance objects
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
@Dao
public interface SubstanceDao {
    /**
     * @Insert
     * @Update
     * @Delete insert, update and delete rows without SQL code (room library)
     */
    @Insert
    long insert(Substance substance);

    @Update
    void update(Substance substance);

    @Delete
    void delete(Substance substance);

    /**
     * @Query methods for special queries
     */
    @Query("DELETE FROM substances")
    void deleteAll();

    @Query("DELETE FROM substances WHERE substanceId = :substanceId")
    void deleteSubstanceById(int substanceId);

    @Query("SELECT * FROM substances")
    LiveData<List<Substance>> getAllSubstances();

    @Query("SELECT * FROM substances WHERE substanceId = :substanceId")
    Substance getSubstanceById(int substanceId);

    @Query("SELECT * FROM substances WHERE title = :title")
    Substance getSubstanceByTitle(String title);
}
