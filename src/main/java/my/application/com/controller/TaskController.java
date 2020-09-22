package my.application.com.controller;

import com.google.gson.Gson;
import my.application.com.model.Goal;
import my.application.com.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public class TaskController {
    private static final Gson gson = new Gson();
    @Autowired
    TaskService taskService;

    @RequestMapping(method = RequestMethod.POST, value = "/add_goal", consumes = "application/json", produces = "application/json")
    public ResponseEntity addTask(@RequestBody Goal goal) throws Exception {
        String jsonTask = gson.toJson(goal, Goal.class);
        taskService.saveAll(goal);
        return new ResponseEntity(gson.toJson(jsonTask), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get_goal")
    public ResponseEntity getTask() {
        List<Goal> goalList = taskService.getAll();
        return new ResponseEntity(gson.toJson(goalList), HttpStatus.OK);
    }
}
