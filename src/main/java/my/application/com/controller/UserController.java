package my.application.com.controller;

import com.google.gson.Gson;
import my.application.com.model.User;
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
public class UserController {
    private static Logger logger = Logger.getLogger(UserController.class);
    private static final Gson gson = new Gson();
    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.GET, value = "/user")
    public ResponseEntity getUser() {
        List<User> userList = userService.getAllByLevel();
        return new ResponseEntity(gson.toJson(userList), HttpStatus.OK);
    }

//    @RequestMapping(method = RequestMethod.GET, value = "/get_user/{name}")
//    public ResponseEntity getUserByName(@PathVariable("name") String name) {
//        User user = userService.findByUserName(name);
//        return new ResponseEntity(gson.toJson(user), HttpStatus.OK);
//    }


    @RequestMapping(method = RequestMethod.GET, value = "/user/{id}")
    public ResponseEntity getUserById(@PathVariable("id") long id) throws Exception {

        if (userService.findUserById(id) != null) {
            return new ResponseEntity(gson.toJson(userService.findUserById(id)), HttpStatus.OK);

        } else throw new Exception("User is not exists");
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/user/{id}")
    public ResponseEntity PutUserById(@PathVariable("id") long id) throws Exception {

        if (userService.findUserById(id) != null) {
            return new ResponseEntity(gson.toJson(userService.findUserById(id)), HttpStatus.OK);

        } else throw new Exception("User is not exists");
    }

    @RequestMapping(method = RequestMethod.POST, value = "/user", consumes = "application/json", produces = "application/json")
    public ResponseEntity addUser(@RequestBody User user, BindingResult bindingResult) throws Exception {
        String jsonUser = gson.toJson(user, User.class);
        if (userService.findByUserName(user.getUserName()) != null) {
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

    @RequestMapping(method = RequestMethod.DELETE, value = "/user/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") long id) throws Exception {
        if (userService.findUserById(id) != null) {
            userService.deleteUserById(id);
        } else throw new Exception("User is not exists");

        return new ResponseEntity(HttpStatus.OK);
    }

}
