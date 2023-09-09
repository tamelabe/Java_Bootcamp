package edu.school21.services;

import edu.school21.models.User;
import edu.school21.repositories.UsersRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;

import static org.mockito.Mockito.*;

public class UserServiceImplTest {
    UsersRepository repository;

    @BeforeEach
    void init() {
        repository = Mockito.mock(UsersRepository.class);
    }

    @Test
    void correctAuth() {
        Mockito.when(repository.findByLogin("Peter")).
                thenReturn(new User("Peter", "sRf404!Qtb", false));
        UserServiceImpl usi = new UserServiceImpl(repository);
        Assertions.assertTrue(usi.authenticate("Peter", "sRf404!Qtb"));
        Mockito.verify(repository).update(Mockito.any());
    }

    @Test
    void incorrectLogin() {
        Mockito.when(repository.findByLogin("Peter")).thenReturn(null);
        UserServiceImpl usi = new UserServiceImpl(repository);
        Assertions.assertFalse(usi.authenticate("Peter", "sRf404!Qtb"));
        Mockito.verify(repository, Mockito.never()).update(Mockito.any());
    }

    @Test
    void incorrectPassword() {
        Mockito.when(repository.findByLogin("Peter")).
                thenReturn(new User("Peter", "sRf404!Qtb", false));
        UserServiceImpl usi = new UserServiceImpl(repository);
        Assertions.assertFalse(usi.authenticate("Peter", "hdfghfhfdg"));
        Mockito.verify(repository, Mockito.never()).update(Mockito.any());
    }
}
