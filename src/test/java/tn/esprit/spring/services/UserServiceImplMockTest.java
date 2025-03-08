package tn.esprit.spring.services;

import java.util.Date;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import tn.esprit.spring.entities.User;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.repository.UserRepository;

@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
class UserServiceImplMockTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void testAddUser() {
        User user = new User(null, "Amine", "Ben Salah", new Date(), Role.ADMINISTRATEUR);
        User savedUser = new User(1L, "Amine", "Ben Salah", new Date(), Role.ADMINISTRATEUR);

        Mockito.when(userRepository.save(ArgumentMatchers.any(User.class))).thenReturn(savedUser);

        User createdUser = userService.addUser(user);

        Assertions.assertNotNull(createdUser);
        Assertions.assertEquals(1L, createdUser.getId());
        Mockito.verify(userRepository, Mockito.times(1)).save(ArgumentMatchers.any(User.class));
    }
    @Test
    public void testUpdateUser() {
        User user = new User("John", "Doe", new Date(), Role.TECHNICIEN);

        // Lenient stub if needed (else remove this stub)
        Mockito.lenient().when(userRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(user));

        userService.updateUser(user);

        // Perform any verifications or assertions you need
        Mockito.verify(userRepository).save(user); // Example of checking save method call
    }

    @Test
    public void testDeleteUser() {
        User user = new User("John", "Doe", new Date(), Role.TECHNICIEN);

        // Lenient stub if needed (else remove this stub)
        Mockito.lenient().when(userRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(user));

        userService.deleteUser(String.valueOf(1L));

        // Verify if deleteById was called
        Mockito.verify(userRepository).deleteById(ArgumentMatchers.anyLong());
    }




}