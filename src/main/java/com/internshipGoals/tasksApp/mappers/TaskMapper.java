package com.internshipGoals.tasksApp.mappers;

import com.internshipGoals.tasksApp.domain.dto.TaskDto;
import com.internshipGoals.tasksApp.domain.entities.Task;

public interface TaskMapper {
  Task fromDto(TaskDto taskDto);

  TaskDto toDto(Task task);
}
