package com.internshipGoals.tasksApp.services;

import com.internshipGoals.tasksApp.domain.entities.TaskList;
import java.util.List;

public interface TaskListService {
  List<TaskList> listTaskLists();
  TaskList createTaskList(TaskList taskList);
}
