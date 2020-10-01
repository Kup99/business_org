package my.application.com.service;

import my.application.com.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    void saveAll(Task task);

    List<Task> getAll();

    void delete(long id) throws Exception;

    Optional<Task> findTaskById(long id);

}
