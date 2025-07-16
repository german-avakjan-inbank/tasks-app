package com.internshipGoals.tasksApp.mappers;

import com.internshipGoals.tasksApp.domain.dto.TaskListDto;
import com.internshipGoals.tasksApp.domain.entities.TaskList;

public interface TaskListMapper {

  TaskList fromDto(TaskListDto taskListDto);

  TaskListDto toDto(TaskList taskList);
}
