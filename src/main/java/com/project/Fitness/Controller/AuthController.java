package com.project.Fitness.Controller;

import com.project.Fitness.Repository.UserRepository;
import com.project.Fitness.Security.JwtUtils;
import com.project.Fitness.Service.UserService;
import com.project.Fitness.dto.LoginRequest;
import com.project.Fitness.dto.LoginResponse;
import com.project.Fitness.dto.RegisterRequest;
import com.project.Fitness.dto.UserResponse;
import com.project.Fitness.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;


    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody RegisterRequest registerRequest){
        return ResponseEntity.ok(userService.register(registerRequest));


    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {

     User user = userService.authentication(loginRequest);
        try {
            String token = jwtUtils.generateJwtToken(user.getId(), user.getRole().name());

            return ResponseEntity.ok(new LoginResponse(token, userService.mapToResponse(user)));

        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).build();
        }


    }








 }
