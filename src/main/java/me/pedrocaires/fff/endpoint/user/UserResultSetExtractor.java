package me.pedrocaires.fff.endpoint.user;

import me.pedrocaires.fff.daoutils.EntityResultSetExtractor;
import me.pedrocaires.fff.endpoint.user.model.User;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserResultSetExtractor extends EntityResultSetExtractor<User> {

	@Override
	public User rawExtraction(ResultSet resultSet) throws SQLException {
		var user = new User();
		user.setId(resultSet.getInt("ID"));
		user.setIdentifier(resultSet.getString("IDENTIFIER"));
		user.setPassword(resultSet.getString("PASSWORD"));
		user.setWebClient(resultSet.getBoolean("WEB_CLIENT"));
		return user;
	}

}
