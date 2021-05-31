package me.pedrocaires.fff.account;

import me.pedrocaires.fff.account.model.CreateAccountRequest;
import me.pedrocaires.fff.account.model.CreateAccountResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountControllerTest {

    @Mock
    AccountService accountService;

    @InjectMocks
    AccountController accountController;

    @Test
    void shouldCallCreateAccount() {
        var createAccountRequest = new CreateAccountRequest();
        var createAccountResponse = new CreateAccountResponse();
        when(accountService.createAccount(createAccountRequest)).thenReturn(createAccountResponse);

        accountController.createAccount(createAccountRequest);

        verify(accountService).createAccount(createAccountRequest);
    }

    @Test
    void shouldReturnOKResponseEntityWithBody() {
        var createAccountRequest = new CreateAccountRequest();
        var createAccountResponse = new CreateAccountResponse();
        when(accountService.createAccount(createAccountRequest)).thenReturn(createAccountResponse);

        var createdAccountResponseEntity = accountController.createAccount(createAccountRequest);

        assertEquals(HttpStatus.OK, createdAccountResponseEntity.getStatusCode());
        assertEquals(createAccountResponse, createdAccountResponseEntity.getBody());
    }
}
