package com.example.demo.user;

import com.example.demo.security.AuthenticationService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationService authenicationService;
    private final RoleDto userDtoService;

    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository, AuthenticationService authenicationService, RoleDto userDtoService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.authenicationService = authenicationService;
        this.userDtoService = userDtoService;
    }

    public void registerUser(String email, String password) {
        User userCreate = new User();

        userCreate.setEmail(email);
        String codePassword = passwordEncoder.encode(password);
        userCreate.setPassword(codePassword);
        List<UserRole> list = Collections.singletonList(new UserRole(userCreate, Role.ROLE_USER));

        userCreate.setRoles(new HashSet<>(list));
        userRepository.save(userCreate);
    }

    public void updatePassword(User user, String password) {
        Optional<User> foundUser = userRepository.findById(user.getId());
        if (foundUser.isPresent()) {
            User userToUpdate = foundUser.get();
            authenicationService.updatePassword(userToUpdate, password);
            userRepository.save(userToUpdate);
        }
    }

    public User getUsersById(Long id) {
        return userRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public void updateUser(Long id, String email) {
        User userToEdit = findById(id);
        userToEdit.setEmail(email);
        userRepository.save(userToEdit);
    }
    public UserDto getUserDtoFromUser(Long id) {
        User userToEdit = getUsersById(id);
        UserDto userDto = userDtoService.userToDto(userToEdit);
        return userDto;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }
}