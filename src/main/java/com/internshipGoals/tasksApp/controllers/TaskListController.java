package com.internshipGoals.tasksApp.controllers;

import com.internshipGoals.tasksApp.domain.dto.TaskListDto;
import com.internshipGoals.tasksApp.mappers.TaskListMapper;
import com.internshipGoals.tasksApp.services.TaskListService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/task-lists")
public class TaskListController {

  private final TaskListService taskListService;
  private final TaskListMapper taskListMapper;

  public TaskListController(TaskListService taskListService, TaskListMapper taskListMapper) {
    this.taskListService = taskListService;
    this.taskListMapper = taskListMapper;
  }

  @GetMapping
  public List<TaskListDto> listTaskLists() {
    return taskListService.listTaskLists()
        .stream()
        .map(taskListMapper::toDto)
        .toList();
  }
}
