package me.pedrocaires.fff.exception;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ExceptionControllerTest {

	@InjectMocks
	ExceptionController exceptionController;

	@Test
	void shouldCatchCustomExceptionsWithResponse() {
		var message = "This is a custom test message";
		var statusCode = HttpStatus.BAD_REQUEST;
		var customException = new CustomException(message, statusCode) {
			@Override
			public HttpStatus getHttpStatus() {
				return super.getHttpStatus();
			}
		};

		var responseEntity = exceptionController.handleCustomException(customException);

		assertEquals(message, responseEntity.getBody().getMessage());
		assertEquals(statusCode, responseEntity.getStatusCode());
	}

	@Test
	void shouldCatchUnusualExceptionsWithFixedResponse() {
		var runtimeException = new RuntimeException();

		var responseEntity = exceptionController.handleException(runtimeException);

		assertEquals(exceptionController.unusualMessage, responseEntity.getBody().getMessage());
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
	}

}
