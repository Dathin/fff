package me.pedrocaires.fff.account;

import me.pedrocaires.fff.account.model.CreateAccountRequest;
import me.pedrocaires.fff.account.model.CreateAccountResponse;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

	private final AccountRepository accountRepository;

	private final AccountMapper accountMapper;

	public AccountService(AccountRepository accountRepository, AccountMapper accountMapper) {
		this.accountRepository = accountRepository;
		this.accountMapper = accountMapper;
	}

	public CreateAccountResponse createAccount(CreateAccountRequest createAccountRequest) {
		var account = accountRepository.insert(createAccountRequest);
		return accountMapper.accountToAccountResponse(account);
	}

}
