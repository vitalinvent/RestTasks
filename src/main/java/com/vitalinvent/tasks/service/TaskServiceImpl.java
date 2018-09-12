package com.vitalinvent.tasks.service;

import com.vitalinvent.tasks.repository.TaskRepository;
import com.vitalinvent.tasks.model.Task;
import com.vitalinvent.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementations of {@link TasksService} interface.
 *
 *
 *
 */

@Service
public class TaskServiceImpl implements TasksService {

    @Autowired
    TaskRepository taskRepository;

    @Override
    public Task getById(String id) {
        return taskRepository.findOne(id);
    }

    @Override
    public void save(Task customer) {
        taskRepository.save(customer);
    }


    @Override
    public List<Task> getAll() {
        return taskRepository.findAll();
    }
}
