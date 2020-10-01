package my.application.com.controller;

import com.google.gson.Gson;
import my.application.com.model.Goal;
import my.application.com.service.GoalService;
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
public class GoalController {
    private static final Gson gson = new Gson();
    @Autowired
    GoalService goalService;

    @RequestMapping(method = RequestMethod.POST, value = "/goal", consumes = "application/json", produces = "application/json")
    public ResponseEntity addGoal
            (@RequestBody Goal goal) throws Exception {
        String jsonGoal = gson.toJson(goal, Goal.class);
        goalService.saveAll(goal);
        return new ResponseEntity(gson.toJson(jsonGoal), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/goal")
    public ResponseEntity getGoal() {
        List<Goal> goalList = goalService.getAll();
        return new ResponseEntity(gson.toJson(goalList), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/goal/{id}")
    public ResponseEntity deleteGoal(@PathVariable("id") long id) throws Exception {
        goalService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/goal/{id}")
    public ResponseEntity getGoalById(@PathVariable("id") long id) throws Exception {
        if (goalService.findGoalById(id) != null) {
            return new ResponseEntity(gson.toJson(goalService.findGoalById(id)), HttpStatus.OK);

        } else throw new Exception("Goal is not exists");
    }

}
