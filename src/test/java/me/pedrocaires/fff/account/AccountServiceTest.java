package me.pedrocaires.fff.account;

import me.pedrocaires.fff.account.model.Account;
import me.pedrocaires.fff.account.model.CreateAccountRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

	PodamFactory podamFactory = new PodamFactoryImpl();

	@Mock
	AccountRepository accountRepository;

	@Spy
	AccountMapper accountMapper = new AccountMapperImpl();

	@InjectMocks
	AccountService accountService;

	@Test
	void shouldInsertAndMapResponse() {
		var createAccountRequest = podamFactory.manufacturePojo(CreateAccountRequest.class);
		var account = podamFactory.manufacturePojo(Account.class);
		when(accountRepository.insert(createAccountRequest)).thenReturn(account);

		accountService.createAccount(createAccountRequest);

		verify(accountRepository).insert(createAccountRequest);
		verify(accountMapper).accountToAccountResponse(account);
	}

}
