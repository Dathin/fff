package me.pedrocaires.fff.controller;

import me.pedrocaires.fff.domain.request.CreateAccountRequest;
import me.pedrocaires.fff.domain.response.CreateAccountResponse;
import me.pedrocaires.fff.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<CreateAccountResponse> createAccount(@RequestBody CreateAccountRequest createAccountRequest){
        return ResponseEntity.ok(accountService.createAccount(createAccountRequest));
    }

}
