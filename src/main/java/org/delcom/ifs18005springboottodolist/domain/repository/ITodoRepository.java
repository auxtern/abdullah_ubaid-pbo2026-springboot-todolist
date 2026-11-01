package org.delcom.ifs18005springboottodolist.domain.repository;

import org.delcom.ifs18005springboottodolist.domain.entity.Todo;

/**
 * Port spesifik Todo yang memperluas generic {@link IRepository}.
 * Menambahkan method pembuatan Todo baru dengan penomoran ID otomatis.
 */
public interface ITodoRepository extends IRepository<Todo, Integer> {
    /**
     * Menyimpan todo baru berdasarkan judul.
     *
     * @param title judul todo yang akan disimpan
     * @return todo yang tersimpan (lengkap dengan ID)
     */
    Todo save(String title);
}
