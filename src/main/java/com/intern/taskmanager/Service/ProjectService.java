package com.intern.taskmanager.Service;

import com.intern.taskmanager.Entity.Project;
import com.intern.taskmanager.Entity.User;
import com.intern.taskmanager.Exception.ResourceNotFoundException;
import com.intern.taskmanager.Repository.ProjectRepository;
import com.intern.taskmanager.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project getProjectById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id " + id));
    }

    public Project addUserToProject(Long projectId, Long userId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (!project.getUsers().contains(user)) {
            project.getUsers().add(user);
        }

        return projectRepository.save(project);
    }
}
