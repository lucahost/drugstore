package ch.ffhs.drugstore.domain.usecase.todo;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.data.entity.Todo;
import ch.ffhs.drugstore.domain.service.TodoService;
import ch.ffhs.drugstore.domain.usecase.UseCase;

public class GetAllTodos implements UseCase<LiveData<List<Todo>>, Void> {
  @Inject TodoService todoService;

  @Inject
  public GetAllTodos(TodoService todoService) {
    this.todoService = todoService;
  }

  @Override
  public LiveData<List<Todo>> execute(Void unused) {
    return todoService.getAllTodos();
  }
}
