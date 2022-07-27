package edu.school21.services;

import edu.school21.exceptions.AlreadyAuthenticatedException;
import edu.school21.models.User;
import edu.school21.repositories.UsersRepository;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.persistence.EntityNotFoundException;

public class UsersServiceImplTest {
    private UsersRepository repository = Mockito.mock(UsersRepository.class);
    private UsersServiceImpl service;
    private final User EXPECTED_CORRECT_USER = new User(1, "Bob", "0000", false);
    private final User EXPECTED_USER_WITH_INCORRECT_LOGIN = new User(2, "Samuel", "0000", false);
    private final User EXPECTED_USER_WITH_INCORRECT_PASS = new User(3, "Bob", "1234", false);
    private final User EXPECTED_INCORRECT_USER = new User(1, "John", "0000", true);

    @BeforeEach
    public void init() {
        service = new UsersServiceImpl(repository);
        Mockito.when(repository.findByLogin(EXPECTED_CORRECT_USER.getLogin())).thenReturn(EXPECTED_CORRECT_USER);
        Mockito.when(repository.findByLogin(EXPECTED_INCORRECT_USER.getLogin())).thenReturn(EXPECTED_INCORRECT_USER);
        Mockito.doNothing().when(repository).update(EXPECTED_CORRECT_USER);
    }

    @Test
    public void authenticateCorrectUserTest() {
        assertTrue(service.authenticate(EXPECTED_CORRECT_USER.getLogin(), EXPECTED_CORRECT_USER.getPassword()));
        Mockito.verify(repository).findByLogin(EXPECTED_CORRECT_USER.getLogin());
        Mockito.verify(repository).update(EXPECTED_CORRECT_USER);
    }

    @Test
    public void authenticateIncorrectLoginTest() {
        assertThrows(EntityNotFoundException.class, () ->
                service.authenticate(EXPECTED_USER_WITH_INCORRECT_LOGIN.getLogin(), EXPECTED_USER_WITH_INCORRECT_LOGIN.getPassword()));
        Mockito.verify(repository).findByLogin(EXPECTED_USER_WITH_INCORRECT_LOGIN.getLogin());
        Mockito.verify(repository, Mockito.never()).update(EXPECTED_USER_WITH_INCORRECT_LOGIN);
    }

    @Test
    public void authenticateIncorrectPasswordTest() {
        assertFalse(service.authenticate(EXPECTED_USER_WITH_INCORRECT_PASS.getLogin(),
                EXPECTED_USER_WITH_INCORRECT_PASS.getPassword()));
        Mockito.verify(repository).findByLogin(EXPECTED_USER_WITH_INCORRECT_PASS.getLogin());
        Mockito.verify(repository, Mockito.never()).update(EXPECTED_USER_WITH_INCORRECT_PASS);
    }

    @Test
    public void authenticateIncorrectUserTest() {
        assertThrows(AlreadyAuthenticatedException.class, () ->
                service.authenticate(EXPECTED_INCORRECT_USER.getLogin(), EXPECTED_INCORRECT_USER.getPassword()));
        Mockito.verify(repository).findByLogin(EXPECTED_INCORRECT_USER.getLogin());
        Mockito.verify(repository, Mockito.never()).update(EXPECTED_INCORRECT_USER);
    }
}
