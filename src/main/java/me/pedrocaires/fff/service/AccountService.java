package me.pedrocaires.fff.service;

import me.pedrocaires.fff.domain.request.CreateAccountRequest;
import me.pedrocaires.fff.domain.response.CreateAccountResponse;
import me.pedrocaires.fff.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public CreateAccountResponse createAccount(CreateAccountRequest createAccountRequest){
        //Usar mapstruct
        var account = accountRepository.insert(createAccountRequest);
        var createAccountResponse = new CreateAccountResponse();
        createAccountResponse.setId(account.getId());
        createAccountResponse.setName(account.getName());
        return createAccountResponse;
    }

}
