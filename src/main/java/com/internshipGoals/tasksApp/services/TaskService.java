package com.internshipGoals.tasksApp.services;

import com.internshipGoals.tasksApp.domain.entities.Task;
import java.util.List;
import java.util.UUID;

public interface TaskService {
  List<Task> listTasks(UUID taskListId);
}
