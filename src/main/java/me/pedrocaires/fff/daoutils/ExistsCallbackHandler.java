package me.pedrocaires.fff.daoutils;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ExistsCallbackHandler implements ResultSetExtractor<Boolean> {

	@Override
	public Boolean extractData(ResultSet resultSet) throws SQLException, DataAccessException {
		resultSet.next();
		return resultSet.getBoolean("EXISTS");
	}

}