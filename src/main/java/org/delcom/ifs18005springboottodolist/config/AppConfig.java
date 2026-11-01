package org.delcom.ifs18005springboottodolist.config;

import org.delcom.ifs18005springboottodolist.adapter.presenter.TodoPresenter;
import org.delcom.ifs18005springboottodolist.adapter.repository.TodoRepository;
import org.delcom.ifs18005springboottodolist.domain.repository.ITodoRepository;
import org.delcom.ifs18005springboottodolist.usecase.TodoUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Composition root Spring: merakit dependency antar-layer.
 */
@Configuration
public class AppConfig {

    @Bean
    public ITodoRepository todoRepository() {
        return new TodoRepository();
    }

    @Bean
    public TodoUseCase todoUseCase(ITodoRepository todoRepository) {
        return new TodoUseCase(todoRepository);
    }

    @Bean
    public TodoPresenter todoPresenter() {
        return new TodoPresenter();
    }
}
