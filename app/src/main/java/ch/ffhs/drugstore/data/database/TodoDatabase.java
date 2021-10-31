package ch.ffhs.drugstore.data.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ch.ffhs.drugstore.data.dao.TodoDao;
import ch.ffhs.drugstore.data.entity.Todo;

@Database(
    entities = {Todo.class},
    version = 1,
    exportSchema = false)
public abstract class TodoDatabase extends RoomDatabase {
  private static final int NUMBER_OF_THREADS = 4;
  public static final ExecutorService databaseWriteExecutor =
      Executors.newFixedThreadPool(NUMBER_OF_THREADS);
  // marking the instance as volatile to ensure atomic access to the variable
  private static volatile TodoDatabase INSTANCE;
  /**
   * Override the onCreate method to populate the database. For this sample, we clear the database
   * every time it is created.
   */
  private static final RoomDatabase.Callback sRoomDatabaseCallback =
      new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
          super.onCreate(db);

          databaseWriteExecutor.execute(
              () -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                TodoDao dao = INSTANCE.todoDao();
                dao.deleteAll();

                Todo todo = new Todo("Do this");
                dao.insert(todo);
                todo = new Todo("And that");
                dao.insert(todo);
              });
        }
      };

  public static TodoDatabase getDatabase(final Context context) {
    if (INSTANCE == null) {
      synchronized (TodoDatabase.class) {
        if (INSTANCE == null) {
          INSTANCE =
              Room.databaseBuilder(
                      context.getApplicationContext(), TodoDatabase.class, "todo_database")
                  .addCallback(sRoomDatabaseCallback)
                  .build();
        }
      }
    }
    return INSTANCE;
  }

  public abstract TodoDao todoDao();
}
