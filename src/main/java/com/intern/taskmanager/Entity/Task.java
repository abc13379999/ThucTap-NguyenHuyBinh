package com.intern.taskmanager.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
//    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference("user-tasks")
    private User user;

    @ManyToOne
    @JoinColumn(name = "project_id")
    @JsonBackReference("project-tasks")
    private Project project;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;
}
