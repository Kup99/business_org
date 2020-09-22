package my.application.com.service;

import my.application.com.model.Goal;
import my.application.com.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public List<Goal> getAll() {
        return taskRepository.findAll();
    }

    @Override
    public void saveAll(Goal goal) {
        taskRepository.save(goal);

    }
}
