package me.pedrocaires.fff.applicationlistener;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ApplicationListenerTest {

    @InjectMocks
    ApplicationListener applicationListener;

    @Test
    void shouldLogAtStartup() {
        applicationListener.onStartup(null);
    }
}