package com.intern.taskmanager.Controller;

import com.intern.taskmanager.DTO.ApiResponse;
import com.intern.taskmanager.DTO.LoginRequest;
import com.intern.taskmanager.DTO.RegisterRequest;
import com.intern.taskmanager.Entity.User;
import com.intern.taskmanager.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<User>> register(@Valid @RequestBody RegisterRequest request) {
        User user = authService.register(request);
        return ResponseEntity.ok(new ApiResponse<>(200, "Đăng ký thành công", user));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> login(@Valid @RequestBody LoginRequest request) {
        String token = authService.login(request);
        return ResponseEntity.ok(new ApiResponse<>(200, "Đăng nhập thành công", token));
    }
}
