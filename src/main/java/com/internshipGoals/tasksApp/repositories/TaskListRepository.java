package com.internshipGoals.tasksApp.repositories;

import com.internshipGoals.tasksApp.domain.entities.TaskList;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskListRepository extends JpaRepository<TaskList, UUID> {
}
