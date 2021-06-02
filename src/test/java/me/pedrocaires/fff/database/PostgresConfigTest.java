package me.pedrocaires.fff.database;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class PostgresConfigTest {

	@Mock
	DataSource dataSource;

	@InjectMocks
	PostgresConfig postgresConfig;

	@Test
	void shouldReturnDataSource() {
		assertNotNull(postgresConfig.postgresDataSource());
	}

	@Test
	void shouldReturnJdbcTemplate() {
		assertNotNull(postgresConfig.postgresJdbcTemplate(dataSource));
	}

}
