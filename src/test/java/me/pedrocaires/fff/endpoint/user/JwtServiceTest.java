package me.pedrocaires.fff.endpoint.user;

import me.pedrocaires.fff.endpoint.user.JwtService;
import me.pedrocaires.fff.exception.UnauthorizedException;
import me.pedrocaires.fff.endpoint.user.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class JwtServiceTest {

	PodamFactory podamFactory = new PodamFactoryImpl();

	JwtService jwtService;

	@BeforeEach
	void setUp() {
		jwtService = new JwtService(
				"testtesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttest");
	}

	@Test
	void shouldExpireTokenInTenMinutes() {
		long tenMinutesInMs = 600000;
		var user = podamFactory.manufacturePojo(User.class);
		;
		var token = jwtService.issueToken(user);

		var userToken = jwtService.validateToken(token);
		var timeBetweenNowAndTokenExpiration = userToken.getExpiresAt().getTime() - new Date().getTime();

		assertEquals(tenMinutesInMs, timeBetweenNowAndTokenExpiration, 10000);
	}

	@Test
	void shouldThrowWhenInvalidToken() {
		assertThrows(UnauthorizedException.class, () -> jwtService.validateToken("invalid token"));
	}

	@Test
	void shouldIssueValidToken() {
		var user = podamFactory.manufacturePojo(User.class);
		;
		user.setId(1);
		var token = jwtService.issueToken(user);

		var userToken = jwtService.validateToken(token);

		assertEquals(user.getId(), userToken.getId());
	}

}