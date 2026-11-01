package org.delcom.ifs18005springboottodolist.adapter.repository;

import org.delcom.ifs18005springboottodolist.domain.entity.Todo;
import org.delcom.ifs18005springboottodolist.domain.repository.ITodoRepository;

/**
 * Implementasi repository todo menggunakan basis generic {@link InMemoryRepository}.
 * Data disimpan di memori (belum menggunakan database).
 */
public class TodoRepository extends InMemoryRepository<Todo, Integer> implements ITodoRepository {
    /** Penghitung ID otomatis, bertambah setiap kali todo baru disimpan. */
    private int idCounter = 0;

    public TodoRepository() {
        super(Todo::getId);
    }

    @Override
    public Todo save(String title) {
        Todo todo = new Todo(nextId(), title);
        return save(todo);
    }

    /** Menghasilkan ID unik berikutnya. */
    private int nextId() {
        return ++idCounter;
    }
}
