package com.intern.taskmanager.Controller;

import com.intern.taskmanager.Entity.Project;
import com.intern.taskmanager.Entity.User;
import com.intern.taskmanager.Service.UserSerive;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserSerive userSerive;

    @PostMapping
    public User createUser(@Valid @RequestBody  User user) {
        return userSerive.createUser(user);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userSerive.getAllUsers();
    }


    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userSerive.getUserById(id);
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Project> projects;
}
