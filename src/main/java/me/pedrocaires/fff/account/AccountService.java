package me.pedrocaires.fff.account;

import me.pedrocaires.fff.account.model.CreateAccountRequest;
import me.pedrocaires.fff.account.model.CreateAccountResponse;
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
