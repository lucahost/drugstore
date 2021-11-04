package ch.ffhs.drugstore.domain.usecase.todo;

import javax.inject.Inject;

import ch.ffhs.drugstore.data.entity.Todo;
import ch.ffhs.drugstore.domain.service.TodoService;
import ch.ffhs.drugstore.domain.usecase.UseCase;

public class RemoveTodo implements UseCase<Void, Todo> {
  @Inject TodoService todoService;

  @Inject
  public RemoveTodo(TodoService todoService) {
    this.todoService = todoService;
  }

  @Override
  public Void execute(Todo todo) {
    todoService.delete(todo);
    return null;
  }
}
