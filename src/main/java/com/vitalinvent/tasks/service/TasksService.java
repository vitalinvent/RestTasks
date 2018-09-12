package com.vitalinvent.tasks.service;

import com.vitalinvent.tasks.model.Task;

import java.util.List;

/**
 * Service interface for {@link Task} class.
 *
 *
 *
 */

public interface TasksService {

    Task getById(String id);

    void save(Task task);

    List<Task> getAll();
}
