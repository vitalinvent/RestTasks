package com.vitalinvent.tasks.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vitalinvent.tasks.model.Task;
import com.vitalinvent.tasks.service.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * REST controller for {@link Task} connected requests.
 *
 * 
 *
 */

@RestController
@RequestMapping("/task/")
public class TaskRestController {

    @Autowired
    private TasksService tasksService;

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Task> getTask(@PathVariable("id") String taskId) {
        if (taskId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try{
            UUID uuid = UUID.fromString(taskId);
        } catch (IllegalArgumentException exception){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Task task = this.tasksService.getById(taskId);

        if (task == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Task> saveTask() {
        HttpHeaders headers = new HttpHeaders();
        ObjectMapper mapper = new ObjectMapper();
        Task task = null;
        try {
            task = mapper.readValue("{\n" +
                    "    \"timestamp\": \""+dateTimeISO8601()+"\",\n" +
                    "    \"status\": \"created\"\n" +
                    "}", Task.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.tasksService.save(task);
        return new ResponseEntity<>(task, headers, HttpStatus.ACCEPTED);
    }
    public static String dateTimeISO8601() {
        DateTimeFormatter dtf = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
        return OffsetDateTime.now().format(dtf);
    }
}
