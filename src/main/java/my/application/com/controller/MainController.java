package my.application.com.controller;

import com.google.gson.Gson;
import my.application.com.model.Task;
import my.application.com.model.User;
import my.application.com.service.TaskService;
import my.application.com.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.acls.model.AlreadyExistsException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


@Controller

public class MainController {
    private static Logger logger = Logger.getLogger(MainController.class);
    private static final Gson gson = new Gson();
    @Autowired
    TaskService taskService;
    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.POST, value = "/add_task", consumes = "application/json", produces = "application/json")
    public ResponseEntity postTask(@RequestBody Task task) throws Exception {
        String jsonTask = gson.toJson(task, Task.class);
        taskService.saveAll(task);
        return new ResponseEntity(gson.toJson(jsonTask), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get_task")
    public ResponseEntity getTask() {
        List<Task> taskList = taskService.getAll();
        return new ResponseEntity(gson.toJson(taskList), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get_user")
    public ResponseEntity getUser() {
        List<User> userList = userService.getAll();
        return new ResponseEntity(gson.toJson(userList), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get_user/{name}")
    public ResponseEntity getUserByName(@PathVariable("name") String name) {
        User user = userService.findByUserName(name);
        return new ResponseEntity(gson.toJson(user), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/add_user", consumes = "application/json", produces = "application/json")
    public ResponseEntity postUser(@RequestBody User user, BindingResult bindingResult) throws Exception {
        String jsonUser = gson.toJson(user, User.class);
        if (userService.userAlreadyExists(user.getUserName())) {
            throw new AlreadyExistsException("User already exists");
        }
        if (bindingResult.hasErrors()) {
            logger.error("Some wrong with post");
            throw new BindException(bindingResult);
        }
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            throw new Exception("Password mismatch");
        }
        userService.saveAll(user);
        return new ResponseEntity(gson.toJson(jsonUser), HttpStatus.OK);
    }
}

