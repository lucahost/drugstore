package ch.ffhs.drugstore.domain.usecase.todo;

import javax.inject.Inject;

import ch.ffhs.drugstore.data.entity.Todo;
import ch.ffhs.drugstore.domain.service.TodoService;
import ch.ffhs.drugstore.domain.usecase.UseCase;

public class CreateTodo implements UseCase<Void, String> {
  @Inject TodoService todoService;

  @Inject
  public CreateTodo(TodoService todoService) {
    this.todoService = todoService;
  }

  @Override
  public Void execute(String text) {
    todoService.insert(new Todo(text));
    return null;
  }
}
