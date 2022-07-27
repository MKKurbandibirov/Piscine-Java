package edu.school21.services;

import javax.persistence.EntityNotFoundException;

import edu.school21.exceptions.AlreadyAuthenticatedException;
import edu.school21.models.User;
import edu.school21.repositories.UsersRepository;

public class UsersServiceImpl {
    private UsersRepository repository;

    public UsersServiceImpl(UsersRepository repository) {
        this.repository = repository;
    }

    public boolean authenticate(String login, String password) {
        User user = repository.findByLogin(login);
        if (user == null) {
            throw new EntityNotFoundException();
        }
        if (user.isAuthenticate()) {
            throw new AlreadyAuthenticatedException("This user already authenticate!");
        }
        if (user.getPassword().equals(password)) {
            user.setAuthenticate(true);
            repository.update(user);
            return true;
        } else {
            return false;
        }
    }
}
