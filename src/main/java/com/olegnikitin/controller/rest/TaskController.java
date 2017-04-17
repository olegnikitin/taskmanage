package com.olegnikitin.controller.rest;

import com.olegnikitin.dao.TaskDao;
import com.olegnikitin.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by olegnikitindev on 06.03.2017.
 */
@RestController
public class TaskController {

    @Autowired
    private TaskDao taskDao;

    @RequestMapping(method = RequestMethod.GET, value = "/tasks", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Task>> list(@RequestParam(required = false) Long limit,
                                           @RequestParam(required = false) Long offset) {
        return ResponseEntity.ok(taskDao.findAll());//TODO: Add params
    }

    @RequestMapping(method = RequestMethod.GET, value = "/tasks/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Task> getTask(@PathVariable String id) {
        if (id == null) return ResponseEntity.notFound().build();
        Task task = taskDao.find(id);
        return task != null ? ResponseEntity.ok(task) : ResponseEntity.notFound().build();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/tasks", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        taskDao.create(task);
        return ResponseEntity.ok(task);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/tasks", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Task> updateTask(@RequestBody Task requestTask) {
        if (requestTask.getId() == null) return ResponseEntity.notFound().build();
        Task task = taskDao.find(requestTask.getId());
        if (task == null) {
            return ResponseEntity.notFound().build();
        } else {
            taskDao.update(task);
            return ResponseEntity.ok(task);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/tasks", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> deleteTask(@RequestParam String id) {
        if (id == null) return ResponseEntity.notFound().build();
        Task task = taskDao.find(id);
        if (task == null) return ResponseEntity.notFound().build();
        else return ResponseEntity.noContent().build();
    }
}
