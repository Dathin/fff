package me.pedrocaires.fff.daoutils.resultsetextractor;

import me.pedrocaires.fff.user.model.User;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserResultSetExtractor extends EntityResultSetExtractor<User> {

	@Override
	User rawExtraction(ResultSet resultSet) throws SQLException {
		var user = new User();
		user.setId(resultSet.getInt("ID"));
		user.setAccountId(resultSet.getInt("ACCOUNT_ID"));
		user.setName(resultSet.getString("NAME"));
		user.setPassword(resultSet.getString("PASSWORD"));
		return user;
	}

}
