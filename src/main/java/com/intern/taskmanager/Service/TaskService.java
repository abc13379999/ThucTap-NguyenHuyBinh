package com.intern.taskmanager.Service;

import com.intern.taskmanager.Entity.Project;
import com.intern.taskmanager.Entity.Task;
import com.intern.taskmanager.Entity.TaskStatus;
import com.intern.taskmanager.Entity.User;
import com.intern.taskmanager.Exception.ResourceNotFoundException;
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

    public Task createSimpleTask(Task task) {
        task.setStatus(TaskStatus.TODO);
        return taskRepository.save(task);
    }


    public Task createTask(Long projectId, Task task) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));

        task.setProject(project);
        task.setStatus(TaskStatus.TODO);

        return taskRepository.save(task);
    }

    public Task assignTask(Long taskId, Long userId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Project project = task.getProject();

        if (!user.getProjects().contains(project)) {
            throw new RuntimeException("User does not belong to this project");
        }

        if (!task.getProject().getUsers().contains(user)) {
            throw new RuntimeException("User not in project");
        }

        task.setUser(user);
        return taskRepository.save(task);
    }

    public Task updateStatus(Long taskId, TaskStatus newStatus) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));

        if (task.getStatus() == TaskStatus.DONE) {
            throw new RuntimeException("Task DONE cannot be updated");
        }

        task.setStatus(newStatus);
        return taskRepository.save(task);
    }


}
