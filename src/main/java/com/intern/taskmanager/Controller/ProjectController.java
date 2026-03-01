package com.intern.taskmanager.Controller;
import com.intern.taskmanager.DTO.ApiResponse;
import com.intern.taskmanager.Entity.Project;
import com.intern.taskmanager.Service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<ApiResponse<Project>> createProject(@Valid @RequestBody Project project) {
        Project created = projectService.createProject(project);
        return ResponseEntity.ok(new ApiResponse<>(200, "Tạo project thành công", created));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Project>>> getAllProjects() {
        return ResponseEntity.ok(new ApiResponse<>(200, "OK", projectService.getAllProjects()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Project>> getProject(@PathVariable Long id) {
        return ResponseEntity.ok(new ApiResponse<>(200, "OK", projectService.getProjectById(id)));
    }

    @PutMapping("/{projectId}/users/{userId}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<ApiResponse<Project>> addUserToProject(
            @PathVariable Long projectId,
            @PathVariable Long userId) {
        Project updated = projectService.addUserToProject(projectId, userId);
        return ResponseEntity.ok(new ApiResponse<>(200, "Thêm user vào project thành công", updated));
    }
}
