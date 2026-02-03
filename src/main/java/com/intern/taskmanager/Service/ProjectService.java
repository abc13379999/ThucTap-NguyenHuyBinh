package com.intern.taskmanager.Service;

import com.intern.taskmanager.Entity.Project;
import com.intern.taskmanager.Entity.User;
import com.intern.taskmanager.Repository.ProjectRepository;
import com.intern.taskmanager.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;

    public Project createProject(Project project) {
        return projectRepository.save(project);
    }
}
