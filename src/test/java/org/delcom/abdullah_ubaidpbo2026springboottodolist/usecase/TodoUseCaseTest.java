package org.delcom.abdullah_ubaidpbo2026springboottodolist.usecase;

import org.delcom.abdullah_ubaidpbo2026springboottodolist.adapter.repository.TodoRepository;
import org.delcom.abdullah_ubaidpbo2026springboottodolist.domain.entity.SortOption;
import org.delcom.abdullah_ubaidpbo2026springboottodolist.domain.entity.Todo;
import org.delcom.abdullah_ubaidpbo2026springboottodolist.domain.exception.EntityNotFoundException;
import org.delcom.abdullah_ubaidpbo2026springboottodolist.domain.exception.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TodoUseCaseTest {

    private TodoUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new TodoUseCase(new TodoRepository());
    }

    @Test
    void addAndGetAllTodos() throws ValidationException {
        Todo todo = useCase.addTodo("Belajar Generic");
        assertEquals(1, todo.getId());
        assertEquals(1, useCase.getAllTodos().size());
    }

    @Test
    void addTodoRejectsBlankTitle() {
        assertThrows(ValidationException.class, () -> useCase.addTodo(" "));
        assertThrows(ValidationException.class, () -> useCase.addTodo(null));
    }

    @Test
    void removeTodoSuccessAndNotFound() throws Exception {
        Todo todo = useCase.addTodo("Hapus");
        useCase.removeTodo(todo.getId());
        assertTrue(useCase.getAllTodos().isEmpty());

        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class, () -> useCase.removeTodo(99));
        assertEquals(99, ex.getId());
    }

    @Test
    void updateTodoPartialAndValidation() throws Exception {
        Todo todo = useCase.addTodo("Lama");

        useCase.updateTodo(todo.getId(), "Baru", true);
        Todo updated = useCase.getAllTodos().get(0);
        assertEquals("Baru", updated.getTitle());
        assertTrue(updated.isFinished());

        useCase.updateTodo(todo.getId(), null, false);
        assertFalse(useCase.getAllTodos().get(0).isFinished());

        useCase.updateTodo(todo.getId(), "Hanya Judul", null);
        assertEquals("Hanya Judul", useCase.getAllTodos().get(0).getTitle());
        assertFalse(useCase.getAllTodos().get(0).isFinished());

        useCase.updateTodo(todo.getId(), null, null);
        assertEquals("Hanya Judul", useCase.getAllTodos().get(0).getTitle());

        assertThrows(ValidationException.class, () -> useCase.updateTodo(todo.getId(), "  ", null));
        assertThrows(EntityNotFoundException.class, () -> useCase.updateTodo(999, "X", null));
    }

    @Test
    void searchAndSortTodos() throws Exception {
        useCase.addTodo("Alpha Task");
        Todo beta = useCase.addTodo("Beta Task");
        useCase.updateTodo(beta.getId(), null, true);

        List<Todo> search = useCase.searchTodos("alpha");
        assertEquals(1, search.size());
        assertEquals("Alpha Task", search.get(0).getTitle());

        List<Todo> titleAsc = useCase.sortTodos(SortOption.TITLE_ASC);
        assertEquals("Alpha Task", titleAsc.get(0).getTitle());

        List<Todo> finishedFirst = useCase.sortTodos(SortOption.FINISHED_FIRST);
        assertTrue(finishedFirst.get(0).isFinished());
    }
}
