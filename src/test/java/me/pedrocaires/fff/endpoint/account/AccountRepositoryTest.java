package me.pedrocaires.fff.endpoint.account;

import me.pedrocaires.fff.endpoint.account.AccountRepository;
import me.pedrocaires.fff.endpoint.account.model.CreateAccountRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountRepositoryTest {

	PodamFactory podamFactory = new PodamFactoryImpl();

	@Mock
	JdbcTemplate jdbcTemplate;

	@InjectMocks
	AccountRepository accountRepository;

	@Test
	void shouldPassAccountInfoToInsert() {
		var createAccountRequest = podamFactory.manufacturePojo(CreateAccountRequest.class);
		createAccountRequest.setName("test-name");

		// accountRepository.insert(createAccountRequest);

		verify(jdbcTemplate).query(any(), any(ResultSetExtractor.class), eq(createAccountRequest.getName()));
	}

}
