package com.automarket.service;

import com.automarket.dto.UserDto;
import com.automarket.exception.DataValidationHandler;
import com.automarket.exception.HibernateValidationException;
import com.automarket.exception.ItemNotFoundException;
import com.automarket.exception.ObjectAlreadyExistsException;
import com.automarket.mapper.UserMapper;
import com.automarket.model.User;
import com.automarket.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper = new UserMapper();

    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserDto> getAllUsers(int page, int pageSize) {
        return userRepository
                .findAll(PageRequest.of(page, pageSize))
                .map(userMapper::UserParseToDto)
                .toList();
    }

    public UserDto addNewUser(User user) {
        validateUser(user);
        validateUserByEmail(user.getEmail());
        validateUserByUsername(user.getUsername());
        validateUserByPhoneNumber(user.getPhoneNumber());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return userMapper.UserParseToDto(user);
    }

    public UserDto updateUserById(long id, User user) {
        checkUserIdExistence(id);
        validateUser(user);
        validateUserByEmail(user.getEmail());
        validateUserByUsername(user.getUsername());
        validateUserByPhoneNumber(user.getPhoneNumber());
        userRepository.save(user);
        return userMapper.UserParseToDto(user);
    }

    public ResponseEntity<?> deleteUserByUserId(long id) {
        checkUserIdExistence(id);
        userRepository.deleteById(id);
        String message = String.format("User with id - '%d' successfully deleted", id);
        return new ResponseEntity<>(Map.of("message", message), HttpStatus.OK);
    }

    private void validateUser(@Valid User user){
        DataValidationHandler<User> validationHandler = new DataValidationHandler<>();
        String validationErrorsMessage = validationHandler.errorsRepresentation(user);
        if (!validationErrorsMessage.isEmpty()){
            throw new HibernateValidationException(validationErrorsMessage, 40902L);
        }
    }

    private void validateUserByEmail(String email){
        if(userRepository.existsByEmail(email)){
            String message = String.format("User with email - '%s' are already exist", email);
            throw new ObjectAlreadyExistsException(message, 40902L);
        }
    }

    private void validateUserByUsername(String username){
        if(userRepository.existsByUsername(username)){
            String message = String.format("User with username - '%s' are already exist", username);
            throw new ObjectAlreadyExistsException(message, 40902L);
        }
    }

    private void validateUserByPhoneNumber(String phoneNumber){
        if(userRepository.existsByPhoneNumber(phoneNumber)){
            String message = String.format("User with phone number - '%s' are already exist", phoneNumber);
            throw new ObjectAlreadyExistsException(message, 40902L);
        }
    }

    private void checkUserIdExistence(long id){
        if(!userRepository.existsById(id)){
            String message = String.format("User with id - '%d' not exist", id);
            throw new ItemNotFoundException(message, 40402L);
        }
    }
}
