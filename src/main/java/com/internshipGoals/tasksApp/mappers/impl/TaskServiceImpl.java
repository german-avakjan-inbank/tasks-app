package com.internshipGoals.tasksApp.mappers.impl;

import com.internshipGoals.tasksApp.domain.entities.Task;
import com.internshipGoals.tasksApp.repositories.TaskRepository;
import com.internshipGoals.tasksApp.services.TaskService;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

  private final TaskRepository taskRepository;

  public TaskServiceImpl(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  @Override
  public List<Task> listTasks(UUID taskListId) {
    return taskRepository.findByTaskListId(taskListId);
  }
}
