package com.internshipGoals.tasksApp.domain.dto;

import com.internshipGoals.tasksApp.domain.entities.TaskPriority;
import com.internshipGoals.tasksApp.domain.entities.TaskStatus;
import java.time.LocalDateTime;
import java.util.UUID;

public record TaskDto(
    UUID id,
    String title,
    String description,
    LocalDateTime dueDate,
    TaskStatus status,
    TaskPriority priority
) {

}
