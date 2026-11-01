package org.delcom.ifs18005springboottodolist.config;

import org.delcom.ifs18005springboottodolist.adapter.presenter.TodoPresenter;
import org.delcom.ifs18005springboottodolist.domain.repository.ITodoRepository;
import org.delcom.ifs18005springboottodolist.usecase.TodoUseCase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class AppConfigTest {

    @Test
    void wiresBeans() {
        AppConfig config = new AppConfig();
        ITodoRepository repository = config.todoRepository();
        TodoUseCase useCase = config.todoUseCase(repository);
        TodoPresenter presenter = config.todoPresenter();

        assertNotNull(repository);
        assertNotNull(useCase);
        assertNotNull(presenter);
        assertInstanceOf(ITodoRepository.class, repository);
    }
}
