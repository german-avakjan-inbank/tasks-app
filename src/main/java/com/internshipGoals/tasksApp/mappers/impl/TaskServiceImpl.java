package com.internshipGoals.tasksApp.mappers.impl;

import com.internshipGoals.tasksApp.domain.entities.Task;
import com.internshipGoals.tasksApp.domain.entities.TaskList;
import com.internshipGoals.tasksApp.domain.entities.TaskPriority;
import com.internshipGoals.tasksApp.domain.entities.TaskStatus;
import com.internshipGoals.tasksApp.repositories.TaskListRepository;
import com.internshipGoals.tasksApp.repositories.TaskRepository;
import com.internshipGoals.tasksApp.services.TaskService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

  private final TaskRepository taskRepository;
  private final TaskListRepository taskListRepository;

  public TaskServiceImpl(TaskRepository taskRepository, TaskListRepository taskListRepository) {
    this.taskRepository = taskRepository;
    this.taskListRepository = taskListRepository;
  }

  @Override
  public List<Task> listTasks(UUID taskListId) {
    return taskRepository.findByTaskListId(taskListId);
  }

  @Override
  public Task createTask(UUID taskListId, Task task) {
    if(null != task.getId()) {
      throw new IllegalArgumentException("Task already exists with ID: " + task.getId());
    }
    if(null == task.getTitle() || task.getTitle().isBlank()) {
      throw new IllegalArgumentException("Task title is required");
    }
    TaskPriority taskPriority = Optional.ofNullable(task.getPriority())
        .orElse(TaskPriority.MEDIUM);

    TaskStatus taskStatus = TaskStatus.OPEN;

    TaskList taskList = taskListRepository.findById(taskListId)
        .orElseThrow(() -> new IllegalArgumentException("Task list ID " + taskListId + " is invalid"));

    LocalDateTime now = LocalDateTime.now();
    Task taskToSave = new Task(
        null,
        task.getTitle(),
        task.getDescription(),
        task.getDueDate(),
        taskStatus,
        taskPriority,
        taskList,
        now,
        now
    );

    return taskRepository.save(taskToSave);
  }

  @Override
  public Optional<Task> getTask(UUID taskListId, UUID taskId) {
    return taskRepository.findByTaskListIdAndId(taskListId, taskId);
  }

  @Override
  public Task updateTask(UUID taskListId, UUID taskId, Task task) {
    if(null == task.getId()) {
      throw new IllegalArgumentException("Task ID is required for update");
    }
    if(!Objects.equals(taskId, task.getId())) {
      throw new IllegalArgumentException("Task IDs do not match");
    }
    if(null == task.getPriority()) {
      throw new IllegalArgumentException("Task priority is required");
    }
    if(null == task.getStatus()) {
      throw new IllegalArgumentException("Task status is required");
    }

    Task existingTask = taskRepository.findByTaskListIdAndId(taskListId, taskId)
        .orElseThrow(() -> new IllegalArgumentException("Task not found"));

    existingTask.setTitle(task.getTitle());
    existingTask.setDescription(task.getDescription());
    existingTask.setDueDate(task.getDueDate());
    existingTask.setPriority(task.getPriority());
    existingTask.setStatus(task.getStatus());
    existingTask.setUpdated(LocalDateTime.now());

    return taskRepository.save(existingTask);
  }
}
