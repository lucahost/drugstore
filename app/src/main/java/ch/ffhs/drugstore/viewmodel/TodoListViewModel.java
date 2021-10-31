package ch.ffhs.drugstore.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import ch.ffhs.drugstore.model.TodoModel;

public class TodoListViewModel extends ViewModel {
  ArrayList<TodoModel> localTodos;
  private MutableLiveData<List<TodoModel>> todos;

  public TodoListViewModel() {
    getAllTodos();
  }

  public LiveData<List<TodoModel>> getTodos() {
    return todos;
  }

  public void addTodo(String text) {
    localTodos.add(new TodoModel(text, false));
    todos.setValue(new ArrayList<>(localTodos));
  }

  public void checkTodo(TodoModel todo) {
    todo.toggle();
    todos.setValue(new ArrayList<>(localTodos));
  }

  public void deleteTodo(TodoModel todo) {
    localTodos.remove(todo);
    todos.setValue(new ArrayList<>(localTodos));
  }

  private void getAllTodos() {
    if (todos == null) {
      todos = new MutableLiveData<>();
    }
    localTodos = new ArrayList<>();
    localTodos.add(new TodoModel("Do this", false));
    localTodos.add(new TodoModel("And that", false));
    localTodos.add(new TodoModel("Never forget this", false));
    localTodos.add(new TodoModel("Maybe also this", false));
    todos.setValue(localTodos);
  }
}
