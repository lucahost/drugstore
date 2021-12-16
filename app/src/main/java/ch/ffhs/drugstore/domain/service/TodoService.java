package ch.ffhs.drugstore.domain.service;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import ch.ffhs.drugstore.data.entity.Todo;
import ch.ffhs.drugstore.data.repository.TodoRepository;
/**
 * This class represents a service to insert, update and return to-do's
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */

public class TodoService {
    private final TodoRepository todoRepository;

    @Inject
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    /**
     *
     * @return
     */
    public LiveData<List<Todo>> getAllTodos() {
        return todoRepository.getAllTodos();
    }

    /**
     *
     * @param todo
     */
    public void insert(Todo todo) {
        todoRepository.insert(todo);
    }

    /**
     *
     * @param todo
     */
    public void update(Todo todo) {
        todoRepository.update(todo);
    }

    /**
     *
     * @param todo
     */
    public void delete(Todo todo) {
        todoRepository.delete(todo);
    }
}
