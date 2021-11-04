package ch.ffhs.drugstore.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.data.dao.TodoDao;
import ch.ffhs.drugstore.data.database.TodoDatabase;
import ch.ffhs.drugstore.data.entity.Todo;

public class TodoRepository {
  private final TodoDao todoDao;
  private final LiveData<List<Todo>> allTodos;

  // Note that in order to unit test the WordRepository, you have to remove the Application
  // dependency. This adds complexity and much more code, and this sample is not about testing.
  // See the BasicSample in the android-architecture-components repository at
  // https://github.com/googlesamples
  @Inject
  public TodoRepository(Application application) {
    TodoDatabase db = TodoDatabase.getDatabase(application);
    todoDao = db.todoDao();
    allTodos = todoDao.getAll();
  }

  // Room executes all queries on a separate thread.
  // Observed LiveData will notify the observer when the data has changed.
  public LiveData<List<Todo>> getAllTodos() {
    return allTodos;
  }

  // You must call this on a non-UI thread or your app will throw an exception. Room ensures
  // that you're not doing any long running operations on the main thread, blocking the UI.
  public void insert(Todo todo) {
    TodoDatabase.databaseWriteExecutor.execute(() -> todoDao.insert(todo));
  }

  public void update(Todo todo) {
    TodoDatabase.databaseWriteExecutor.execute(() -> todoDao.update(todo));
  }

  public void delete(Todo todo) {
    TodoDatabase.databaseWriteExecutor.execute(() -> todoDao.delete(todo));
  }

  public void deleteAll() {
    TodoDatabase.databaseWriteExecutor.execute(todoDao::deleteAll);
  }
}
