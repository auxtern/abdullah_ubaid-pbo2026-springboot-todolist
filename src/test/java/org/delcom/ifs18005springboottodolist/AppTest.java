package org.delcom.ifs18005springboottodolist;

import org.delcom.ifs18005springboottodolist.adapter.presenter.TodoPresenter;
import org.delcom.ifs18005springboottodolist.domain.repository.ITodoRepository;
import org.delcom.ifs18005springboottodolist.usecase.TodoUseCase;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;

@SpringBootTest
class AppTest {

    @Autowired
    private ITodoRepository todoRepository;

    @Autowired
    private TodoUseCase todoUseCase;

    @Autowired
    private TodoPresenter todoPresenter;

    @Test
    void contextLoadsAndWiresCleanArchitectureBeans() {
        assertNotNull(todoRepository);
        assertNotNull(todoUseCase);
        assertNotNull(todoPresenter);
    }

    @Test
    void mainDelegatesToSpringApplicationRun() throws Exception {
        ConfigurableApplicationContext context = mock(ConfigurableApplicationContext.class);
        try (MockedStatic<SpringApplication> mocked = mockStatic(SpringApplication.class)) {
            mocked.when(() -> SpringApplication.run(
                    eq(App.class),
                    any(String[].class)
            )).thenReturn(context);

            App.main(new String[]{"--server.port=0"});

            mocked.verify(() -> SpringApplication.run(
                    eq(App.class),
                    any(String[].class)
            ));
        }
    }
}
