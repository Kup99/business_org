package my.application.com.controller;

import com.google.gson.Gson;
import my.application.com.model.Task;
import my.application.com.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class TaskController {
    private static final Gson gson = new Gson();
    @Autowired
    TaskService taskService;

    @RequestMapping(method = RequestMethod.POST, value = "/task", consumes = "application/json", produces = "application/json")
    public ResponseEntity addTask
            (@RequestBody Task task) throws Exception {
        String jsonTask = gson.toJson(task, Task.class);
        taskService.saveAll(task);
        return new ResponseEntity(gson.toJson(jsonTask), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/task")
    public ResponseEntity getTask() {
        List<Task> taskList = taskService.getAll();
        return new ResponseEntity(gson.toJson(taskList), HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.DELETE, value = "/task/{id}")
    public ResponseEntity deleteTask(@PathVariable("id") long id) throws Exception {
        taskService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/task/{id}")
    public ResponseEntity getTaskById(@PathVariable("id") long id) throws Exception {
        if (taskService.findTaskById(id) != null) {
            return new ResponseEntity(gson.toJson(taskService.findTaskById(id)), HttpStatus.OK);

        } else throw new Exception("Task is not exists");
    }
}
