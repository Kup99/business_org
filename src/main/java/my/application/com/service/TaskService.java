package my.application.com.service;

import my.application.com.model.Task;
import my.application.com.model.User;

import java.util.List;


public interface TaskService {
    void saveAll(Task task);

    List<Task> getAll();
}
