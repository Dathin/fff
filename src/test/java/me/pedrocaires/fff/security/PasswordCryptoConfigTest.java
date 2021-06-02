package me.pedrocaires.fff.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class PasswordCryptoConfigTest {

	@InjectMocks
	PasswordCryptoConfig passwordCryptoConfig;

	PasswordEncoder passwordEncoder;

	@BeforeEach
	void setUp() {
		passwordEncoder = passwordCryptoConfig.encoder();
	}

	@Test
	void shouldReturnPasswordEncoder() {
		assertNotNull(passwordEncoder);
	}

}