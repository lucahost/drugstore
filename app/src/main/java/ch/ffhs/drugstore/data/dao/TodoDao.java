package ch.ffhs.drugstore.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import ch.ffhs.drugstore.data.entity.Todo;

@Dao
public interface TodoDao {

  @Insert
  void insert(Todo todo);

  @Update
  void update(Todo todo);

  @Delete
  void delete(Todo todo);

  @Query("DELETE FROM todo_table")
  void deleteAll();

  @Query("SELECT *, `rowid` FROM todo_table")
  LiveData<List<Todo>> getAll();
}
