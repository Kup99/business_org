package my.application.com.service;

import my.application.com.model.Task;
import my.application.com.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Override
    public void saveAll(Task task) {
        taskRepository.save(task);
    }

    @Override
    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    @Override
    public void delete(long id) throws Exception {
        Optional<Task> task = taskRepository.findById(id);
        if (!task.isPresent()) {
            throw new Exception("Task is not exists");
        }
        taskRepository.deleteById(id);
    }

    @Override
    public Optional<Task> findTaskById(long id) {
        Optional<Task> task = taskRepository.findById(id);
        return task;
    }
}
