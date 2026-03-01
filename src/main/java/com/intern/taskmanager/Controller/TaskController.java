package com.intern.taskmanager.Controller;

import com.intern.taskmanager.DTO.ApiResponse;
import com.intern.taskmanager.DTO.TaskRequest;
import com.intern.taskmanager.Entity.Task;
import com.intern.taskmanager.Entity.TaskStatus;
import com.intern.taskmanager.Service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    // Tạo task: userId và projectId truyền qua body (TaskRequest)
    @PostMapping
    public ResponseEntity<ApiResponse<Task>> createTask(@Valid @RequestBody TaskRequest taskRequest) {
        Task task = taskService.createTask(
                taskRequest.getUserId(),
                taskRequest.getProjectId(),
                taskRequest
        );
        return ResponseEntity.ok(new ApiResponse<>(200, "Tạo task thành công", task));
    }

    // Lấy task theo user hoặc project: GET /api/tasks?userId=1 hoặc GET /api/tasks?projectId=2
    @GetMapping
    public ResponseEntity<ApiResponse<List<Task>>> getTasks(
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) Long projectId) {

        if (userId != null) {
            return ResponseEntity.ok(new ApiResponse<>(200, "OK", taskService.getTasksByUser(userId)));
        }
        if (projectId != null) {
            return ResponseEntity.ok(new ApiResponse<>(200, "OK", taskService.getTasksByProject(projectId)));
        }
        return ResponseEntity.badRequest().body(new ApiResponse<>(400, "Cần truyền userId hoặc projectId", null));
    }

    // Cập nhật task
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Task>> updateTask(
            @PathVariable Long id,
            @RequestBody Task updatedTask) {
        return ResponseEntity.ok(new ApiResponse<>(200, "Cập nhật thành công", taskService.updateTask(id, updatedTask)));
    }

    // Xóa task
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok(new ApiResponse<>(200, "Xóa task thành công", null));
    }

    // Assign task cho user (chỉ MANAGER)
    @PutMapping("/{id}/assign/{userId}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<ApiResponse<Task>> assignTask(
            @PathVariable Long id,
            @PathVariable Long userId) {
        return ResponseEntity.ok(new ApiResponse<>(200, "Assign thành công", taskService.assignTask(id, userId)));
    }

    // Cập nhật status của task (dùng PATCH vì chỉ update 1 field)
    @PatchMapping("/{id}/status")
    public ResponseEntity<ApiResponse<Task>> updateStatus(
            @PathVariable Long id,
            @RequestParam TaskStatus status) {
        return ResponseEntity.ok(new ApiResponse<>(200, "Cập nhật status thành công", taskService.updateStatus(id, status)));
    }
}
