package com.intern.taskmanager.Service;

import com.intern.taskmanager.DTO.LoginRequest;
import com.intern.taskmanager.DTO.RegisterRequest;
import com.intern.taskmanager.Entity.Role;
import com.intern.taskmanager.Entity.User;
import com.intern.taskmanager.Exception.BadRequestException;
import com.intern.taskmanager.Exception.ResourceNotFoundException;
import com.intern.taskmanager.Repository.RoleRepository;
import com.intern.taskmanager.Repository.UserRepository;
import com.intern.taskmanager.Security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public User register(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new BadRequestException("Email đã được sử dụng");
        }

        String roleName = (request.getRole() != null) ? request.getRole().toUpperCase() : "USER";
        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new ResourceNotFoundException("Role không tồn tại: " + roleName));

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(role)
                .build();

        return userRepository.save(user);
    }

    public String login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BadRequestException("Email hoặc password không đúng"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadRequestException("Email hoặc password không đúng");
        }

        // Tạo JWT token chứa email + role
        return jwtUtil.generateToken(user.getEmail(), user.getRole().getName());
    }
}
