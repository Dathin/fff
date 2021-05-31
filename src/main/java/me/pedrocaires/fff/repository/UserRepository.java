package me.pedrocaires.fff.repository;

import me.pedrocaires.fff.domain.entity.User;
import me.pedrocaires.fff.domain.request.CreateUserRequest;
import me.pedrocaires.fff.domain.request.LoginRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;
    private final ReturningIdCallbackHandler returningIdCallbackHandler;

    public UserRepository(JdbcTemplate jdbcTemplate, ReturningIdCallbackHandler returningIdCallbackHandler) {
        this.jdbcTemplate = jdbcTemplate;
        this.returningIdCallbackHandler = returningIdCallbackHandler;
    }

    public int insert(CreateUserRequest createUserRequest){
        return jdbcTemplate.query("INSERT INTO USERS (NAME, ACCOUNT_ID, PASSWORD) VALUES (?, ?, ?) RETURNING ID",
                returningIdCallbackHandler, createUserRequest.getName(), createUserRequest.getAccountId(), createUserRequest.getPassword());
    }

    public Optional<User> getPasswordToLogin(LoginRequest loginRequest){
        return jdbcTemplate.query("SELECT * FROM USERS WHERE ACCOUNT_ID = ? AND NAME = ? LIMIT 1", resultSet -> {
            if (resultSet.next()){
                var user = new User();
                user.setId(resultSet.getInt("ID"));
                user.setAccountId(resultSet.getInt("ACCOUNT_ID"));
                user.setName(resultSet.getString("NAME"));
                user.setPassword(resultSet.getString("PASSWORD"));
                return Optional.of(user);
            }
            return Optional.empty();
        }, loginRequest.getAccountId(), loginRequest.getName());
    }
}
