package com.internshipGoals.tasksApp.mappers.impl;

import com.internshipGoals.tasksApp.domain.dto.TaskDto;
import com.internshipGoals.tasksApp.domain.entities.Task;
import com.internshipGoals.tasksApp.mappers.TaskMapper;
import org.springframework.stereotype.Component;

@Component
public class TaskMapperImpl implements TaskMapper {

  @Override
  public Task fromDto(TaskDto taskDto) {
    return new Task(
        taskDto.id(),
        taskDto.title(),
        taskDto.description(),
        taskDto.dueDate(),
        taskDto.status(),
        taskDto.priority(),
        null,
        null,
        null
    );
  }

  @Override
  public TaskDto toDto(Task task) {
    return new TaskDto(
        task.getId(),
        task.getTitle(),
        task.getDescription(),
        task.getDueDate(),
        task.getStatus(),
        task.getPriority()
    );
  }
}
