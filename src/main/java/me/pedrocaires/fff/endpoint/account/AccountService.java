package me.pedrocaires.fff.endpoint.account;

import me.pedrocaires.fff.endpoint.account.model.Account;
import me.pedrocaires.fff.endpoint.account.model.AccountAlreadyExistException;
import me.pedrocaires.fff.endpoint.account.model.CreateAccountRequest;
import me.pedrocaires.fff.endpoint.user.UserService;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

	private final AccountRepository accountRepository;

	private final UserService userService;

	public AccountService(AccountRepository accountRepository, UserService userService) {
		this.accountRepository = accountRepository;
		this.userService = userService;
	}

	public Account createAccount(CreateAccountRequest createAccountRequest) {
		try {
			return accountRepository.insert(createAccountRequest, userService.getOrThrowAuthenticatedUser().getId());
		}
		catch (DuplicateKeyException ex) {
			throw new AccountAlreadyExistException();
		}
	}

}
