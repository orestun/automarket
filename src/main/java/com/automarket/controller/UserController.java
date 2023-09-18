package com.automarket.controller;

import com.automarket.dto.UserDto;
import com.automarket.mapper.UserMapper;
import com.automarket.model.User;
import com.automarket.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "user")
public class UserController {

    private final UserMapper userMapper = new UserMapper();
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDto> getAllUser(
            @RequestParam(name = "page", defaultValue = "1", required = false) int page,
            @RequestParam(name = "page-size", defaultValue = "10", required = false) int pageSize){
        return userService
                .getAllUsers(page, pageSize);
    }

    @PostMapping
    public UserDto addNewUser(@RequestBody UserDto userDto){
        User user = userMapper.UserParseFromDto(userDto);
        return userService.addNewUser(user);
    }

    @PatchMapping("{id}")
    public UserDto updateUserById(
            @PathVariable("id") long id,
            @RequestBody UserDto userDto){
        User user = userMapper.UserParseFromDto(userDto);
        return userService.updateUserById(id, user);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteUserByUserId(
            @PathVariable("id") long id){
        return userService.deleteUserByUserId(id);
    }
}
