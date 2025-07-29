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
}
