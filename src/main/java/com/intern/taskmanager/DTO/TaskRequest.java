package com.intern.taskmanager.DTO;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskRequest {
    @NotBlank(message = "Title must not be blank")
    @Size(max = 50, message = "Title max 50 characters")
    private String title;

    private String description;

    @Future(message = "Deadline must be in the future")
    private LocalDate deadline;

    private Long projectId;
    private Long userId;
}
