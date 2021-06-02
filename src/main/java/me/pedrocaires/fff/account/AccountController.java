package me.pedrocaires.fff.account;

import me.pedrocaires.fff.account.model.CreateAccountRequest;
import me.pedrocaires.fff.account.model.CreateAccountResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

	private final AccountService accountService;

	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}

	@PostMapping
	public ResponseEntity<CreateAccountResponse> createAccount(@RequestBody CreateAccountRequest createAccountRequest) {
		return ResponseEntity.ok(accountService.createAccount(createAccountRequest));
	}

}
