package com.vitalinvent.tasks.repository;

import com.vitalinvent.tasks.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for {@link Task} class.
 *
 *
 *
 */
public interface TaskRepository extends JpaRepository<Task, String> {
}
