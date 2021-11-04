package ch.ffhs.drugstore.domain.usecase.todo;

import javax.inject.Inject;

import ch.ffhs.drugstore.data.entity.Todo;
import ch.ffhs.drugstore.domain.service.TodoService;
import ch.ffhs.drugstore.domain.usecase.UseCase;

public class ToggleTodo implements UseCase<Void, Todo> {
  @Inject TodoService todoService;

  @Inject
  public ToggleTodo(TodoService todoService) {
    this.todoService = todoService;
  }

  @Override
  public Void execute(Todo todo) {
    todo.toggle();
    todoService.update(todo);
    return null;
  }
}
