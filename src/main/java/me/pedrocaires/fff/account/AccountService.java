package me.pedrocaires.fff.account;

import me.pedrocaires.fff.account.model.Account;
import me.pedrocaires.fff.account.model.CreateAccountRequest;
import me.pedrocaires.fff.exception.alreadyexist.AccountAlreadyExistException;
import me.pedrocaires.fff.exception.alreadyexist.FeatureFlagAlreadyExistException;
import me.pedrocaires.fff.user.UserService;
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
		} catch (DuplicateKeyException ex) {
			throw new AccountAlreadyExistException();
		}
	}

}
