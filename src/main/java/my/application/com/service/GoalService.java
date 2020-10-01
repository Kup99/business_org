package my.application.com.service;

import my.application.com.model.Goal;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;


public interface GoalService {
    void saveAll(Goal goal);

    List<Goal> getAll();

    void delete(long id) throws Exception;

    Optional<Goal> findGoalById(long id) throws ParseException;

}
