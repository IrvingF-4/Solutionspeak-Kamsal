package com.solutionspeak.Kamsal.Workshop.service;

import com.solutionspeak.Kamsal.Workshop.dto.UserDTO;
import com.solutionspeak.Kamsal.Workshop.entity.User;
import com.solutionspeak.Kamsal.Workshop.form.UserForm;
import com.solutionspeak.Kamsal.Workshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<UserDTO> findAllusers() {
        final List<User> users = userRepository.findAll();
        return users.stream().map(UserDTO::build).toList();
    }

    public UserDTO getUserById(final int userId) throws Exception {
        validateIfUserExist(userId);
        final User user = userRepository.findById(userId).orElseThrow();
        return UserDTO.build(user);
    }

    public UserDTO createUser(final UserForm form) {
        final User user = new User(form);
        userRepository.save(user);
        return UserDTO.build(user);
    }

    public UserDTO updateUser(final UserForm form, final int userId) throws Exception {
        validateIfUserExist(userId);
        final User user = userRepository.findById(userId).orElseThrow();
        user.updateUser(form);
        userRepository.save(user);
        return UserDTO.build(user);
    }

    public void deleteUser(final int userId) throws Exception {
        validateIfUserExist(userId);
        userRepository.deleteById(userId);
    }

    public UserDTO loginUser(final String email, final String password) throws Exception {
        validateIfEmailAndPasswordExist(email, password);
        final User user = userRepository.getUserByEmailAndPassword(email, password);
        return UserDTO.build(user);
    }

    public Map<Integer, UserDTO> getUserByIds(final List<Integer> usersId) {
        final List<User> users = userRepository.findAllById(usersId);
        return userDTOs(users);
    }

    public void validateIfUserExist(final int userId) throws Exception {
        if (!userRepository.existsById(userId)) {
            throw new Exception("No se ha encontrado el usuario con el ID: " + userId);
        }
    }

    public void validateIfEmailAndPasswordExist(String email, String password) throws Exception {
        if (!userRepository.existsByEmailAndPassword(email, password)) {
            throw new Exception("No se ha encontrado el usuario");
        }
    }

    private Map<Integer, UserDTO> userDTOs(final List<User> users) {
        final List<UserDTO> userDTOS = users.stream().map(UserDTO::build).toList();
        return userDTOS.stream()
                .collect(Collectors.toMap(UserDTO::getId, Function.identity()));
    }
}