package me.pedrocaires.fff.endpoint.account;

import me.pedrocaires.fff.daoutils.EntityResultSetExtractor;
import me.pedrocaires.fff.endpoint.account.model.Account;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class AccountResultSetExtractor extends EntityResultSetExtractor<Account> {

	@Override
	public Account rawExtraction(ResultSet resultSet) throws SQLException {
		var account = new Account();
		account.setId(resultSet.getInt("ID"));
		account.setName(resultSet.getString("NAME"));
		return account;
	}

}
