package me.pedrocaires.fff.user;

import me.pedrocaires.fff.daoutils.ReturningCountCallbackHandler;
import me.pedrocaires.fff.daoutils.ReturningIdCallbackHandler;
import me.pedrocaires.fff.user.model.CreateUserRequest;
import me.pedrocaires.fff.user.model.LoginRequest;
import me.pedrocaires.fff.user.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;
    private final ReturningCountCallbackHandler returningCountCallbackHandler;

    private final ResultSetExtractor<Optional<User>> optionalUserResultSetExtractor = resultSet -> {
        if (resultSet.next()){
            var user = resultSetUser(resultSet);
            return Optional.of(user);
        }
        return Optional.empty();
    };

    private final ResultSetExtractor<User> userResultSetExtractor = resultSet -> {
        resultSet.next();
        return resultSetUser(resultSet);
    };

    public UserRepository(JdbcTemplate jdbcTemplate, ReturningIdCallbackHandler returningIdCallbackHandler, ReturningCountCallbackHandler returningCountCallbackHandler) {
        this.jdbcTemplate = jdbcTemplate;
        this.returningCountCallbackHandler = returningCountCallbackHandler;
    }

    public User insert(CreateUserRequest createUserRequest){
        return jdbcTemplate.query("INSERT INTO USERS (NAME, ACCOUNT_ID, PASSWORD) VALUES (?, ?, ?) RETURNING ID, NAME, ACCOUNT_ID, PASSWORD",
                userResultSetExtractor, createUserRequest.getName(), createUserRequest.getAccountId(), createUserRequest.getPassword());
    }

    public Optional<User> getPasswordToLogin(LoginRequest loginRequest){
        return jdbcTemplate.query("SELECT * FROM USERS WHERE ACCOUNT_ID = ? AND NAME = ? LIMIT 1", optionalUserResultSetExtractor, loginRequest.getAccountId(), loginRequest.getName());
    }

    public int countByAccountId(int accountId){
        return jdbcTemplate.query("SELECT COUNT(1) FROM USERS WHERE ACCOUNT_ID = ?", returningCountCallbackHandler, accountId);
    }

    private User resultSetUser(ResultSet resultSet) throws SQLException {
        var user = new User();
        user.setId(resultSet.getInt("ID"));
        user.setAccountId(resultSet.getInt("ACCOUNT_ID"));
        user.setName(resultSet.getString("NAME"));
        user.setPassword(resultSet.getString("PASSWORD"));
        return user;
    }
}
