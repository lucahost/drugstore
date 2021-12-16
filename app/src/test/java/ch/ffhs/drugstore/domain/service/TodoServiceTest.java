package ch.ffhs.drugstore.domain.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import ch.ffhs.drugstore.data.entity.Todo;
import ch.ffhs.drugstore.data.repository.TodoRepository;
/**
 * Test-class for TodoService class
 *
 *  @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 *  @version 2021.12.15
 */
public class TodoServiceTest {

    private LiveData<List<Todo>> todo;
    private TodoRepository todoRepository;
    private TodoService todoService;

    public TodoServiceTest() {
        todo = new MutableLiveData<>();
        todoRepository = mock(TodoRepository.class);
        when(todoRepository.getAllTodos()).thenReturn(todo);
        todoService = new TodoService(todoRepository);
    }


    @Before
    public void setUp() {
        LiveData<List<Todo>> todo = new MutableLiveData<>();
        TodoRepository todoRepository = mock(TodoRepository.class);
        when(todoRepository.getAllTodos()).thenReturn(todo);
        todoService = new TodoService(todoRepository);
    }


    @Test
    public void getAllTodos() {

        // Test
        LiveData<List<Todo>> result = todoService.getAllTodos();

        // Assert
        verify(todoRepository, times(1)).getAllTodos();
    }

    @Test
    public void insert() {

        // Test

        // Assert


    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }
}