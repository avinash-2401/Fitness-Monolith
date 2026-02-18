package com.project.Fitness.Service;

import com.project.Fitness.Repository.UserRepository;
import com.project.Fitness.dto.LoginRequest;
import com.project.Fitness.dto.RegisterRequest;
import com.project.Fitness.dto.UserResponse;
import com.project.Fitness.model.User;
import com.project.Fitness.model.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * =========================
     * REGISTER USER
     * =========================
     */
    public UserResponse register(RegisterRequest request) {

        // default role
        UserRole role = request.getRole() != null
                ? request.getRole()
                : UserRole.USER;

        // create user entity
        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword())) // 🔐 encode password
                .role(role)
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .build();

        User savedUser = userRepository.save(user);

        return mapToResponse(savedUser);
    }

    /**
     * =========================
     * LOGIN AUTHENTICATION
     * =========================
     */
    public User authentication(LoginRequest loginRequest) {

        // find user by email
        User user = userRepository.findByEmail(loginRequest.getEmail());

        if (user == null) {
            throw new RuntimeException("Invalid Credential");
        }

        // compare raw password with encoded password
        boolean isMatch = passwordEncoder.matches(
                loginRequest.getPassword(),
                user.getPassword()
        );

        if (!isMatch) {
            throw new RuntimeException("Invalid Credential");
        }

        return user;
    }

    /**
     * =========================
     * MAP ENTITY -> RESPONSE DTO
     * =========================
     */
    public UserResponse mapToResponse(User savedUser) {

        UserResponse response = new UserResponse();

        response.setId(savedUser.getId());
        response.setEmail(savedUser.getEmail());

        // ❌ NEVER expose password in response
        // response.setPassword(savedUser.getPassword());

        response.setFirstName(savedUser.getFirstName());
        response.setLastName(savedUser.getLastName());
        response.setCreatedAt(savedUser.getCreatedAt());
        response.setUpdatedAt(savedUser.getUpdatedAt());

        return response;
    }
}
