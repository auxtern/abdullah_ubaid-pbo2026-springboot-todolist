package org.delcom.abdullah_ubaidpbo2026springboottodolist.domain.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TodoTest {

    @Test
    void constructorDefaultsFinishedToFalse() {
        Todo todo = new Todo(1, "Belajar Spring Boot");

        assertEquals(1, todo.getId());
        assertEquals("Belajar Spring Boot", todo.getTitle());
        assertFalse(todo.isFinished());
        assertNotNull(todo.getCreatedAt());
        assertNull(todo.getFinishedAt());
        assertTrue(todo.duration().isEmpty());
    }

    @Test
    void constructorWithFinishedFlagSetsFinishedAt() {
        Todo todo = new Todo(2, "Selesai", true);

        assertTrue(todo.isFinished());
        assertNotNull(todo.getFinishedAt());
        assertTrue(todo.duration().isPresent());
    }

    @Test
    void changeTitleAndFinishedUpdatesTimestamps() {
        LocalDateTime created = LocalDateTime.of(2026, 11, 1, 8, 0);
        Todo todo = new Todo(3, "Lama", false, created, null);

        todo.changeTitle("Baru");
        todo.changeFinished(true);

        assertEquals("Baru", todo.getTitle());
        assertTrue(todo.isFinished());
        assertNotNull(todo.getFinishedAt());
        assertTrue(todo.duration().isPresent());

        LocalDateTime firstFinishedAt = todo.getFinishedAt();
        todo.changeFinished(true);
        assertEquals(firstFinishedAt, todo.getFinishedAt());

        todo.changeFinished(false);
        assertFalse(todo.isFinished());
        assertNull(todo.getFinishedAt());
        assertTrue(todo.duration().isEmpty());
    }

    @Test
    void durationUsesCreatedAndFinishedAt() {
        LocalDateTime created = LocalDateTime.of(2026, 11, 1, 8, 0);
        LocalDateTime finished = LocalDateTime.of(2026, 11, 1, 10, 30);
        Todo todo = new Todo(4, "Durasi", true, created, finished);

        assertEquals(150, todo.duration().orElseThrow().toMinutes());
    }
}
