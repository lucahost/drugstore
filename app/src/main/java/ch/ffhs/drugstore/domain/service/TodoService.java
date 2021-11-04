package ch.ffhs.drugstore.domain.service;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.data.entity.Todo;
import ch.ffhs.drugstore.data.repository.TodoRepository;

public class TodoService {
  @Inject TodoRepository todoRepository;

  @Inject
  public TodoService() {
    // TODO document why this constructor is empty
  }

  public LiveData<List<Todo>> getAllTodos() {
    return todoRepository.getAllTodos();
  }

  public void insert(Todo todo) {
    todoRepository.insert(todo);
  }

  public void update(Todo todo) {
    todoRepository.update(todo);
  }

  public void delete(Todo todo) {
    todoRepository.delete(todo);
  }
}
