package me.pedrocaires.fff.endpoint.account;

import me.pedrocaires.fff.endpoint.account.model.Account;
import me.pedrocaires.fff.endpoint.account.model.AccountResponse;
import me.pedrocaires.fff.endpoint.account.model.CreateAccountResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {

	CreateAccountResponse accountToAccountResponse(Account account);

	List<AccountResponse> accountsToAccountResponses(List<Account> accounts);

}
