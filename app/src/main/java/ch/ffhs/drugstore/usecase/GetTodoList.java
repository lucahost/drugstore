package ch.ffhs.drugstore.usecase;

import java.util.ArrayList;
import java.util.List;

import ch.ffhs.drugstore.model.TodoModel;

public class GetTodoList extends UseCase<List<TodoModel>, Void> {
  @Override
  public List<TodoModel> execute(Void unused) {
    ArrayList<TodoModel> todos = new ArrayList<>();
    return todos;
  }
}
