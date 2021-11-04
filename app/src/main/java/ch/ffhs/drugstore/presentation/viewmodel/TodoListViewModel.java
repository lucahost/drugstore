package ch.ffhs.drugstore.presentation.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.data.entity.Todo;
import ch.ffhs.drugstore.domain.usecase.todo.CreateTodo;
import ch.ffhs.drugstore.domain.usecase.todo.GetAllTodos;
import ch.ffhs.drugstore.domain.usecase.todo.RemoveTodo;
import ch.ffhs.drugstore.domain.usecase.todo.ToggleTodo;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class TodoListViewModel extends AndroidViewModel {
  @Inject CreateTodo createTodo;
  @Inject GetAllTodos getAllTodos;
  @Inject RemoveTodo removeTodo;
  @Inject ToggleTodo toggleTodo;

  private LiveData<List<Todo>> todos;

  @Inject
  public TodoListViewModel(Application application) {
    super(application);
  }

  public LiveData<List<Todo>> getTodos() {
    if (todos == null) {
      todos = getAllTodos.execute(null);
    }
    return todos;
  }

  public void addTodo(String text) {
    createTodo.execute(text);
  }

  public void checkTodo(Todo todo) {
    toggleTodo.execute(todo);
  }

  public void deleteTodo(Todo todo) {
    removeTodo.execute(todo);
  }
}
