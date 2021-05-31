package me.pedrocaires.fff.account;

import me.pedrocaires.fff.account.model.CreateAccountRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AccountRepositoryTest {

    @Mock
    JdbcTemplate jdbcTemplate;

    @InjectMocks
    AccountRepository accountRepository;

    @Test
    void shouldPassAccountInfoToInsert() {
        var createAccountRequest = new CreateAccountRequest();
        createAccountRequest.setName("test-name");

        var account =  accountRepository.insert(createAccountRequest);

        verify(jdbcTemplate).query(any(), any(ResultSetExtractor.class), eq(createAccountRequest.getName()));
    }
}
