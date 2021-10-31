package ch.ffhs.drugstore.presentation.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import ch.ffhs.drugstore.data.entity.Todo;
import ch.ffhs.drugstore.data.repository.TodoRepository;

public class TodoListViewModel extends AndroidViewModel {
  private final TodoRepository todoRepository;
  private final LiveData<List<Todo>> todos;

  public TodoListViewModel(Application application) {
    super(application);
    todoRepository = new TodoRepository(application);
    todos = todoRepository.getAllTodos();
  }

  public LiveData<List<Todo>> getTodos() {
    return todos;
  }

  public void addTodo(String text) {
    todoRepository.insert(new Todo(text));
  }

  public void checkTodo(Todo todo) {
    todo.toggle();
    todoRepository.update(todo);
  }

  public void deleteTodo(Todo todo) {
    todoRepository.delete(todo);
  }
}
