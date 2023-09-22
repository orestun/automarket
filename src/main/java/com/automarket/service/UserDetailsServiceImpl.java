package com.automarket.service;

import com.automarket.exception.ItemNotFoundException;
import com.automarket.model.User;
import com.automarket.model.UserDetailsImpl;
import com.automarket.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<User> user = userRepository.findByEmail(username);
        return user.map(UserDetailsImpl::new)
                .orElseThrow(
                        () -> new ItemNotFoundException(
                                String.format("Username with email -'%s' are not found", username),
                                40404L));
    }
}
