package com.internshipGoals.tasksApp.controllers;

import static org.aspectj.weaver.tools.cache.SimpleCacheFactory.path;

import com.internshipGoals.tasksApp.domain.dto.TaskDto;
import com.internshipGoals.tasksApp.domain.entities.Task;
import com.internshipGoals.tasksApp.mappers.TaskMapper;
import com.internshipGoals.tasksApp.services.TaskService;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/task-lists/{taskListId}/tasks")
public class TasksController {
  private final TaskService taskService;
  private final TaskMapper taskMapper;

  public TasksController(TaskService taskService, TaskMapper taskMapper) {
    this.taskService = taskService;
    this.taskMapper = taskMapper;
  }

  @GetMapping
  public List<TaskDto> listTasks(@PathVariable("task_list_id") UUID taskListId) {
    return taskService.listTasks(taskListId)
        .stream()
        .map(taskMapper::toDto)
        .toList();
  }

  @PostMapping
  public TaskDto createTask(
      @PathVariable("tasl_list_id") UUID taskListId,
      @RequestBody TaskDto taskDto) {
    Task createdTask = taskService.createTask(
        taskListId,
        taskMapper.fromDto(taskDto)
    );
    return taskMapper.toDto(createdTask);
  }

  @GetMapping(path = "/{task_id}")
  public Optional<TaskDto> getTask(
      @PathVariable("task_list_id") UUID taskListId,
      @PathVariable("task_id") UUID taskId
  ) {
    return taskService.getTask(taskListId, taskId).map(taskMapper::toDto);
  }

  @PutMapping(path = "/{task_id}")
  public TaskDto updateTask(
      @PathVariable("task_list_id") UUID taskListId,
      @PathVariable("task_id") UUID taskId,
      @RequestBody TaskDto taskDto
  ) {
    Task updatedTask = taskService.updateTask(
        taskListId, taskId, taskMapper.fromDto(taskDto)
    );
    return taskMapper.toDto(updatedTask);
  }

  @DeleteMapping(path = "/{task_id}")
  public void deleteTask(
      @PathVariable("task_list_id") UUID taskListId,
      @PathVariable("task_id") UUID taskId
  ) {
    taskService.deleteTask(taskListId, taskId);
  }
}
