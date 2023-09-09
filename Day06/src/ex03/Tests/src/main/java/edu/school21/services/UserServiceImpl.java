package edu.school21.services;

import edu.school21.exceptions.AlreadyAuthenticatedException;
import edu.school21.models.User;
import edu.school21.repositories.UsersRepository;

public class UserServiceImpl {
    UsersRepository ur;
    UserServiceImpl(UsersRepository ur) {
        this.ur = ur;
    }
    boolean authenticate(String login, String password) {
        User user = ur.findByLogin(login);
        if (user == null) { return false; }
        if (user.isAuthStatus()) {
            throw new AlreadyAuthenticatedException("User already authenticated");
        } else if (user.getPassword().equals(password)) {
            user.setAuthStatus(true);
            ur.update(user);
            return true;
        } else {
            return false;
        }
    }
}
