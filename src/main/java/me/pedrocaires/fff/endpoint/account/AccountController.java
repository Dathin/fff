package me.pedrocaires.fff.endpoint.account;

import me.pedrocaires.fff.endpoint.account.model.CreateAccountRequest;
import me.pedrocaires.fff.endpoint.account.model.CreateAccountResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/account")
public class AccountController {

	private final AccountService accountService;

	private final AccountMapper accountMapper;

	public AccountController(AccountService accountService, AccountMapper accountMapper) {
		this.accountService = accountService;
		this.accountMapper = accountMapper;
	}

	@PostMapping
	public ResponseEntity<CreateAccountResponse> createAccount(
			@Valid @RequestBody CreateAccountRequest createAccountRequest) {
		var createdAccount = accountService.createAccount(createAccountRequest);
		return ResponseEntity.ok(accountMapper.accountToAccountResponse(createdAccount));
	}

}
