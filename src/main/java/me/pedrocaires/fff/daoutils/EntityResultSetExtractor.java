package me.pedrocaires.fff.daoutils;

import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class EntityResultSetExtractor<T> {

	protected EntityResultSetExtractor() {
	}

	public abstract T rawExtraction(ResultSet resultSet) throws SQLException;

	public ResultSetExtractor<T> extractObject() {
		return resultSet -> {
			resultSet.next();
			return rawExtraction(resultSet);
		};
	}

	public ResultSetExtractor<List<T>> extractList() {
		return resultSet -> {
			var list = new ArrayList<T>();
			while (resultSet.next()) {
				list.add(rawExtraction(resultSet));
			}
			return list;
		};
	}

	public ResultSetExtractor<Optional<T>> extractOptional() {
		return resultSet -> {
			if (resultSet.next()) {
				return Optional.of(rawExtraction(resultSet));
			}
			return Optional.empty();
		};
	}

}
