package com.internshipGoals.tasksApp.mappers.impl;

import com.internshipGoals.tasksApp.domain.entities.TaskList;
import com.internshipGoals.tasksApp.repositories.TaskListRepository;
import com.internshipGoals.tasksApp.services.TaskListService;
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
}
