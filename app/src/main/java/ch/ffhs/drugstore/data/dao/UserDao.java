package ch.ffhs.drugstore.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import ch.ffhs.drugstore.data.entity.User;

/**
 * data access object (DAO) class abstracts access to the database for the User objects
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
@Dao
public interface UserDao {

    @Insert
    long insert(User user);

    @Query("SELECT * FROM users")
    LiveData<List<User>> getAllUsers();

    @Query("SELECT * FROM users WHERE userId = :userId")
    User getUserById(int userId);

    @Query("SELECT * FROM users WHERE shortName = :shortName")
    User getUserByShortName(String shortName);
}
