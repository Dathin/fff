package me.pedrocaires.fff.daoutils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReturningCountCallbackHandlerTest {

	@Mock
	ResultSet resultSet;

	@InjectMocks
	ReturningCountCallbackHandler returningCountCallbackHandler;

	@Test
	void shouldReturnCount() throws SQLException {
		var expectedCount = 10;
		when(resultSet.getInt(1)).thenReturn(10);

		var count = returningCountCallbackHandler.extractData(resultSet);

		assertEquals(expectedCount, count);
	}

}
