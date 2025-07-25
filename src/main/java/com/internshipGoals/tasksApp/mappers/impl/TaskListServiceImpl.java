package com.internshipGoals.tasksApp.mappers.impl;

import com.internshipGoals.tasksApp.domain.entities.TaskList;
import com.internshipGoals.tasksApp.repositories.TaskListRepository;
import com.internshipGoals.tasksApp.services.TaskListService;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TaskListServiceImpl implements TaskListService {

  private final TaskListRepository taskListRepository;

  public TaskListServiceImpl(TaskListRepository taskListRepository) {
    this.taskListRepository = taskListRepository;
  }

  @Override
  public List<TaskList> listTaskLists() {
    return taskListRepository.findAll();
  }

  @Override
  public TaskList createTaskList(TaskList taskList) {
    if (null != taskList.getId()) {
      throw new IllegalArgumentException("Task list already exists");
    }
    if (null == taskList.getTitle() || taskList.getTitle().isBlank()) {
      throw new IllegalArgumentException("Task list title cannot be empty");
    }

    LocalDateTime now = LocalDateTime.now();
    return taskListRepository.save(new TaskList(
        null,
        taskList.getTitle(),
        taskList.getDescription(),
        null,
        now,
        now
    ));
  }
}
