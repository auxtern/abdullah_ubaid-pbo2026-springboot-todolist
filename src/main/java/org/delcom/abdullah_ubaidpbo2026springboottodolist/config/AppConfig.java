package org.delcom.abdullah_ubaidpbo2026springboottodolist.config;

import org.delcom.abdullah_ubaidpbo2026springboottodolist.adapter.presenter.TodoPresenter;
import org.delcom.abdullah_ubaidpbo2026springboottodolist.adapter.repository.TodoRepository;
import org.delcom.abdullah_ubaidpbo2026springboottodolist.domain.repository.ITodoRepository;
import org.delcom.abdullah_ubaidpbo2026springboottodolist.usecase.TodoUseCase;
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
