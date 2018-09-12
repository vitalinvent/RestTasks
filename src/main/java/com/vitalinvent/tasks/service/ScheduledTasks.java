package com.vitalinvent.tasks.service;

import com.vitalinvent.tasks.model.Status;
import com.vitalinvent.tasks.model.Task;
import org.joda.time.DateTime;
import org.joda.time.Seconds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.joda.time.LocalDateTime;

@Component
public class ScheduledTasks {

    @Autowired
    private TasksService tasksService;

    public static String dateTimeISO8601() {
        DateTimeFormatter dtf = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
        return OffsetDateTime.now().format(dtf);
    }

    //@Scheduled(fixedRate = 120000)
    @Scheduled(fixedRate = 1000)
    public void checkTasks(){
        List<Task> tasks = this.tasksService.getAll();
        for (Task task:tasks) {
            DateTime taskDateTime= DateTime.parse(task.getTimestamp());
            DateTime currentDateTime= DateTime.parse(dateTimeISO8601());
            int seconds = Seconds.secondsBetween(taskDateTime,currentDateTime).getSeconds();
            if ((seconds>120) && (task.getStatus().contains(Status.created.toString()))){
                task.setStatus(Status.running);
                task.setTimestamp();
                tasksService.save(task);
            }else if ((seconds>120) && (task.getStatus().contains(Status.running.toString()))){
                task.setStatus(Status.finished);
                task.setTimestamp();
                tasksService.save(task);
            }
        }
    }
}
