package org.delcom.abdullah_ubaidpbo2026springboottodolist.domain.entity;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Entity inti yang merepresentasikan satu item todo.
 * Berada di layer domain — tidak bergantung pada layer lain dan bebas dari
 * urusan tampilan maupun penyimpanan.
 */
public class Todo {
    /** ID unik todo, tidak boleh diubah setelah dibuat. */
    private final int id;

    /** Judul atau deskripsi tugas. */
    private String title;

    /** Status penyelesaian: true = selesai, false = belum selesai. */
    private boolean finished;

    /** Waktu todo dibuat. */
    private final LocalDateTime createdAt;

    /** Waktu todo ditandai selesai; null jika belum selesai. */
    private LocalDateTime finishedAt;

    /** Membuat todo baru dengan status belum selesai. */
    public Todo(int id, String title) {
        this(id, title, false);
    }

    /** Membuat todo dengan status selesai yang sudah ditentukan. */
    public Todo(int id, String title, boolean finished) {
        this(id, title, finished, LocalDateTime.now(), finished ? LocalDateTime.now() : null);
    }

    /** Membuat todo dengan waktu dibuat dan selesai yang sudah ditentukan. */
    public Todo(int id, String title, boolean finished, LocalDateTime createdAt, LocalDateTime finishedAt) {
        this.id = id;
        this.title = title;
        this.finished = finished;
        this.createdAt = createdAt;
        this.finishedAt = finishedAt;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isFinished() {
        return finished;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getFinishedAt() {
        return finishedAt;
    }

    /**
     * Menghitung rentang waktu dari tanggal dibuat sampai tanggal selesai.
     * Kosong jika todo belum selesai.
     */
    public Optional<Duration> duration() {
        if (finishedAt == null) {
            return Optional.empty();
        }
        return Optional.of(Duration.between(createdAt, finishedAt));
    }

    /** Mengubah judul todo. */
    public void changeTitle(String title) {
        this.title = title;
    }

    /** Mengubah status selesai todo dan memperbarui tanggal selesai. */
    public void changeFinished(boolean finished) {
        this.finished = finished;
        if (finished) {
            if (this.finishedAt == null) {
                this.finishedAt = LocalDateTime.now();
            }
        } else {
            this.finishedAt = null;
        }
    }
}
