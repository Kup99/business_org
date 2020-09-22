package my.application.com.service;

import my.application.com.model.Goal;

import java.util.List;


public interface TaskService {
    void saveAll(Goal goal);

    List<Goal> getAll();
}
