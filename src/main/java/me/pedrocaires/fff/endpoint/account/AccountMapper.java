package me.pedrocaires.fff.endpoint.account;

import me.pedrocaires.fff.endpoint.account.model.Account;
import me.pedrocaires.fff.endpoint.account.model.CreateAccountResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {

	CreateAccountResponse accountToAccountResponse(Account account);

}
