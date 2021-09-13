package me.pedrocaires.fff.endpoint.account;

import me.pedrocaires.fff.endpoint.account.model.AccountResponse;
import me.pedrocaires.fff.endpoint.account.model.CreateAccountRequest;
import me.pedrocaires.fff.endpoint.account.model.CreateAccountResponse;
import me.pedrocaires.fff.endpoint.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

	private final AccountService accountService;

	private final UserService userService;

	private final AccountMapper accountMapper;

	public AccountController(AccountService accountService, UserService userService, AccountMapper accountMapper) {
		this.accountService = accountService;
		this.userService = userService;
		this.accountMapper = accountMapper;
	}

	@PostMapping
	public ResponseEntity<CreateAccountResponse> createAccount(
			@Valid @RequestBody CreateAccountRequest createAccountRequest) {
		var createdAccount = accountService.createAccount(createAccountRequest);
		return ResponseEntity.ok(accountMapper.accountToAccountResponse(createdAccount));
	}

	@GetMapping
	public ResponseEntity<List<AccountResponse>> accounts() {
		var userId = userService.getOrThrowAuthenticatedUser().getId();
		var accounts = accountService.getAccountsFromUserId(userId);
		return ResponseEntity.ok(accountMapper.accountsToAccountResponses(accounts));
	}

}
