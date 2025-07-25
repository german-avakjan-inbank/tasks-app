package com.internshipGoals.tasksApp.controllers;

import com.internshipGoals.tasksApp.domain.dto.TaskListDto;
import com.internshipGoals.tasksApp.domain.entities.TaskList;
import com.internshipGoals.tasksApp.mappers.TaskListMapper;
import com.internshipGoals.tasksApp.services.TaskListService;
import java.util.List;
import java.util.UUID;
import java.util.Optional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

  @PostMapping
  public TaskListDto createTaskList(@RequestBody TaskListDto taskListDto) {
    TaskList createdTaskList = taskListService.createTaskList(
        taskListMapper.fromDto(taskListDto)
    );
    return taskListMapper.toDto(createdTaskList);
  }

  @GetMapping(path = "/{task_list_id}")
  public Optional<TaskListDto> getTaskList(@PathVariable("task_list_id") UUID taskListId) {
    return taskListService.getTaskList(taskListId).map(taskListMapper::toDto);
  }

  @PutMapping(path = "/{task_list_id}")
  public TaskListDto updtateTaskList(
      @PathVariable("task_list_id") UUID taskListId,
      @RequestBody TaskListDto taskListDto
  ) {
    TaskList updatedTaskList = taskListService.updateTaskList(
        taskListId,
        taskListMapper.fromDto(taskListDto)
    );
    return taskListMapper.toDto(updatedTaskList);
  }
}
