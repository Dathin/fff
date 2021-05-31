package me.pedrocaires.fff.repository;

import me.pedrocaires.fff.domain.entity.User;
import me.pedrocaires.fff.domain.request.CreateUserRequest;
import me.pedrocaires.fff.domain.request.LoginRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;
    private final ReturningIdCallbackHandler returningIdCallbackHandler;
    private final ReturningCountCallbackHandler returningCountCallbackHandler;

    private final ResultSetExtractor<Optional<User>> optionalUserResultSetExtractor = resultSet -> {
        if (resultSet.next()){
            var user = resultSetUser(resultSet);
            return Optional.of(user);
        }
        return Optional.empty();
    };

    private final ResultSetExtractor<User> userResultSetExtractor = this::resultSetUser;

    public UserRepository(JdbcTemplate jdbcTemplate, ReturningIdCallbackHandler returningIdCallbackHandler, ReturningCountCallbackHandler returningCountCallbackHandler) {
        this.jdbcTemplate = jdbcTemplate;
        this.returningIdCallbackHandler = returningIdCallbackHandler;
        this.returningCountCallbackHandler = returningCountCallbackHandler;
    }

    public User insert(CreateUserRequest createUserRequest){
        return jdbcTemplate.query("INSERT INTO USERS (NAME, ACCOUNT_ID, PASSWORD) VALUES (?, ?, ?) RETURNING ID",
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
