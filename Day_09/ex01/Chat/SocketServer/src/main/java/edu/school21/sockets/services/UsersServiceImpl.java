package edu.school21.sockets.services;

import edu.school21.sockets.models.Message;
import edu.school21.sockets.models.User;
import edu.school21.sockets.repositories.MessagesRepository;
import edu.school21.sockets.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class UsersServiceImpl implements UsersService {
    private UsersRepository usersRepository;
    private MessagesRepository messagesRepository;

    @Autowired
    public UsersServiceImpl(UsersRepository repository, MessagesRepository messagesRepository) {
        this.usersRepository = repository;
        this.messagesRepository = messagesRepository;
    }

    @Override
    public boolean signUp(String username, String password) {
        if (usersRepository.findByLogin(username).isPresent()) {
            return false;
        }
        usersRepository.save(new User(null, username, password));
        return true;
    }

    @Override
    public boolean signIn(String username, String password) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        Optional<User> opt = usersRepository.findByLogin(username);

        if (opt.isPresent() && encoder.matches(password, opt.get().getPassword())) {
            return true;
        }
        return false;
    }

    @Override
    public void createMessage(String message) {
        messagesRepository.save(new Message(null, message, LocalDateTime.now()));
    }
}
