package org.delcom.ifs18005springboottodolist.adapter.repository;

import org.delcom.ifs18005springboottodolist.domain.entity.Todo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TodoRepositoryTest {

    private TodoRepository repository;

    @BeforeEach
    void setUp() {
        repository = new TodoRepository();
    }

    @Test
    void saveByTitleAssignsIncrementalIds() {
        Todo first = repository.save("Satu");
        Todo second = repository.save("Dua");

        assertEquals(1, first.getId());
        assertEquals(2, second.getId());
        assertEquals(2, repository.findAll().size());
    }

    @Test
    void findByIdAndDelete() {
        Todo todo = repository.save("Hapus saya");

        Optional<Todo> found = repository.findById(todo.getId());
        assertTrue(found.isPresent());
        assertEquals("Hapus saya", found.get().getTitle());

        assertTrue(repository.deleteById(todo.getId()));
        assertFalse(repository.deleteById(todo.getId()));
        assertTrue(repository.findById(todo.getId()).isEmpty());
    }

    @Test
    void findByPredicateAndUpdateNoOp() {
        repository.save("Belajar Java");
        repository.save("Belajar Spring");

        List<Todo> springTodos = repository.findBy(todo -> todo.getTitle().contains("Spring"));
        assertEquals(1, springTodos.size());

        Todo todo = springTodos.get(0);
        todo.changeTitle("Belajar Spring Boot");
        repository.update(todo);

        assertEquals("Belajar Spring Boot", repository.findById(todo.getId()).orElseThrow().getTitle());
    }

    @Test
    void findAllReturnsDefensiveCopy() {
        repository.save("A");
        List<Todo> all = repository.findAll();
        all.clear();
        assertEquals(1, repository.findAll().size());
    }

    @Test
    void saveEntityDirectly() {
        Todo entity = new Todo(99, "Manual");
        repository.save(entity);
        assertTrue(repository.findById(99).isPresent());
    }
}
