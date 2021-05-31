package me.pedrocaires.fff.swagger;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class SwaggerConfigTest {

    @InjectMocks
    SwaggerConfig swaggerConfig;

    @Test
    void shouldReturnDocket() {
        assertNotNull(swaggerConfig.fffApi());
    }

}
