package com.automarket.security.auth;

import com.automarket.exception.BadAuthenticationData;
import com.automarket.exception.ObjectAlreadyExistsException;
import com.automarket.model.Role;
import com.automarket.model.User;
import com.automarket.repository.UserRepository;
import com.automarket.security.JwtTokenService;
import com.automarket.utils.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest registerRequest) {
        if(userRepository.existsByEmail(registerRequest.getEmail())){
            String message = String.format("User with email - '%s' are already exists", registerRequest.getEmail());
            throw new ObjectAlreadyExistsException(message, 40901L);
        }
        if(userRepository.existsByUsername(registerRequest.getUsername())){
            String message = String.format("User with username - '%s' are already exists", registerRequest.getUsername());
            throw new ObjectAlreadyExistsException(message, 40901L);
        }
        User user = User
                .builder()
                .email(registerRequest.getEmail())
                .username(registerRequest.getUsername())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .roles(List.of(new Role(Roles.ROLE_USER)))
                .build();
        userRepository.save(user);
        String token = jwtService.generateToken(user.getEmail());
        return new AuthenticationResponse(token);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authRequest) {
        Authentication authentication = getAndTryAuthentication(authRequest);
        if(authentication.isAuthenticated()){
            String token = jwtService.generateToken(authRequest.getEmail());
           return new AuthenticationResponse(token);
        }else{
            throw new BadAuthenticationData("Bad input auth data", 40101L);
        }

    }

    private Authentication getAndTryAuthentication(AuthenticationRequest authenticationRequest){
        try{
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getEmail(),
                            authenticationRequest.getPassword()
                    )
            );
        }catch (BadCredentialsException e){
            throw new BadAuthenticationData("Bad input credentials", 40101L);
        }
    }
}
