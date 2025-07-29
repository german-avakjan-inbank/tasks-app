package com.internshipGoals.tasksApp.mappers.impl;

import com.internshipGoals.tasksApp.domain.entities.TaskList;
import com.internshipGoals.tasksApp.repositories.TaskListRepository;
import com.internshipGoals.tasksApp.services.TaskListService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

  @Override
  public Optional<TaskList> getTaskList(UUID id) {
    return taskListRepository.findById(id);
  }

  @Transactional
  @Override
  public TaskList updateTaskList(UUID taskListId, TaskList taskList) {
    if (null == taskList.getId()) {
      throw new IllegalArgumentException("Task list must have an ID");
    }

    if (!taskListId.equals(taskList.getId())) {
      throw new IllegalArgumentException("Task list ID does not match the provided ID");
    }

    TaskList existingTaskList = taskListRepository.findById(taskListId).orElseThrow(() ->
        new IllegalArgumentException("Task list not found"));

    existingTaskList.setTitle(taskList.getTitle());
    existingTaskList.setDescription(taskList.getDescription());
    existingTaskList.setUpdated(LocalDateTime.now());
    return taskListRepository.save(existingTaskList);
  }

  @Override
  public void deleteTaskList(UUID taskListId) {
    taskListRepository.deleteById(taskListId);
  }
}
