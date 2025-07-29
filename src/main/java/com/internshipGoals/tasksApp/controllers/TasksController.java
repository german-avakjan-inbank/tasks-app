package com.internshipGoals.tasksApp.controllers;

import static org.aspectj.weaver.tools.cache.SimpleCacheFactory.path;

import com.internshipGoals.tasksApp.domain.dto.TaskDto;
import com.internshipGoals.tasksApp.mappers.TaskMapper;
import com.internshipGoals.tasksApp.services.TaskService;
import java.util.List;
import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
}
