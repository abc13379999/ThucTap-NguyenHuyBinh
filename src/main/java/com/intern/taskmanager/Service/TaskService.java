package com.intern.taskmanager.Service;

import com.intern.taskmanager.Entity.Project;
import com.intern.taskmanager.Entity.Task;
import com.intern.taskmanager.Entity.User;
import com.intern.taskmanager.Repository.ProjectRepository;
import com.intern.taskmanager.Repository.TaskRepository;
import com.intern.taskmanager.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    public Task createTask(Long userId, Long projectId, Task task) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        task.setUser(user);
        task.setProject(project);

        return taskRepository.save(task);
    }

    public List<Task> getTasksByUser(Long userId) {
        return taskRepository.findByUserId(userId);
    }

    public List<Task> getTasksByProject(Long projectId) {
        return taskRepository.findByProjectId(projectId);
    }

    public Task updateTask(Long taskId, Task updatedTask) {
        Task existingTask = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found with id " + taskId));

        existingTask.setTitle(updatedTask.getTitle());
        existingTask.setStatus(updatedTask.getStatus());

        return taskRepository.save(existingTask);
    }

    public void deleteTask(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found with id " + taskId));

        taskRepository.delete(task);
    }
}
