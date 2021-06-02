package me.pedrocaires.fff.account;

import me.pedrocaires.fff.account.model.Account;
import me.pedrocaires.fff.account.model.CreateAccountRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

	@Mock
	AccountRepository accountRepository;

	@Mock
	AccountMapper accountMapper;

	@InjectMocks
	AccountService accountService;

	@Test
	void shouldInsertAndMapResponse() {
		var createAccountRequest = new CreateAccountRequest();
		var account = new Account();
		when(accountRepository.insert(createAccountRequest)).thenReturn(account);

		accountService.createAccount(createAccountRequest);

		verify(accountRepository).insert(createAccountRequest);
		verify(accountMapper).accountToAccountResponse(account);
	}

}
