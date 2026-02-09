package com.intern.taskmanager.Controller;

import com.intern.taskmanager.Entity.Task;
import com.intern.taskmanager.Entity.TaskStatus;
import com.intern.taskmanager.Service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping("/user/{userId}/project/{projectId}")
    public Task createTask(@PathVariable Long userId,
                           @PathVariable Long projectId,
                           @RequestBody Task task) {
        return taskService.createTask(userId, projectId, task);
    }

    @GetMapping("/user/{userId}")
    public List<Task> getTasksByUser(@PathVariable Long userId) {
        return taskService.getTasksByUser(userId);
    }

    @GetMapping("/project/{projectId}")
    public List<Task> getTasksByProject(@PathVariable Long projectId) {
        return taskService.getTasksByProject(projectId);
    }

    @PutMapping("/{taskId}")
    public Task updateTask(@PathVariable Long taskId, @RequestBody Task updatedTask) {
        return taskService.updateTask(taskId, updatedTask);
    }

    @DeleteMapping("/{taskId}")
    public void deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
    }

    @PostMapping("/project/{projectId}")
    public Task createTask(@PathVariable Long projectId, @RequestBody Task task) {
        return taskService.createTask(projectId, task);
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskService.createSimpleTask(task);
    }

    @PutMapping("/{taskId}/assign/{userId}")
    public Task assignTask(@PathVariable Long taskId, @PathVariable Long userId) {
        return taskService.assignTask(taskId, userId);
    }

    @PutMapping("/{taskId}/status")
    public Task updateStatus(
            @PathVariable Long taskId,
            @RequestParam TaskStatus status
    ) {
        return taskService.updateStatus(taskId, status);
    }

}
