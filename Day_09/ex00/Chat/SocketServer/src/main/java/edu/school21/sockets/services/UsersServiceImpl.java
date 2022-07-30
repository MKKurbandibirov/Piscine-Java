package edu.school21.sockets.services;

import edu.school21.sockets.models.User;
import edu.school21.sockets.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("usersService")
public class UsersServiceImpl implements UsersService{
    private UsersRepository repository;

    @Autowired
    public UsersServiceImpl(UsersRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean signUp(String username, String password) {
        if (repository.findByLogin(username).isPresent()) {
            return false;
        }
        repository.save(new User(null, username, password));
        return true;
    }
}
