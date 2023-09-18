package com.automarket.mapper;

import com.automarket.dto.UserDto;
import com.automarket.model.User;

public class UserMapper {

    public UserDto UserParseToDto(User user){
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());
        userDto.setFirthName(user.getFirthName());
        userDto.setSecondName(user.getSecondName());
        userDto.setEmail(user.getEmail());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setPhotoURL(user.getPhotoURL());
        userDto.setVehiclesForSale(user.getVehiclesForSale());
        return userDto;
    }

    public User UserParseFromDto(UserDto userDto){
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setFirthName(userDto.getFirthName());
        user.setSecondName(userDto.getSecondName());
        user.setEmail(userDto.getEmail());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setPhotoURL(userDto.getPhotoURL());
        return user;
    }
}