package my.application.com.service;

import my.application.com.model.Goal;
import my.application.com.repository.GoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public class GoalServiceImpl implements GoalService {

    @Autowired
    private GoalRepository goalRepository;

    @Override
    public List<Goal> getAll() {
        return goalRepository.findAll();
    }

    @Override
    public void delete(long id) throws Exception {
        Optional<Goal> goal = goalRepository.findById(id);
        if (!goal.isPresent()) {
            throw new Exception("Goal is not exists");
        }
        goalRepository.deleteById(id);
    }

    @Override
    public Optional<Goal> findGoalById(long id) throws ParseException {
        Optional<Goal> goal = goalRepository.findById(id);
        goal.get().setDaysForGoal(parseDateDaysDifference(goal.get().getGoalDate(), new Date()));
        return goal;
    }

    @Override
    public void saveAll(Goal goal) {
        goalRepository.save(goal);

    }

    private int parseDateDaysDifference(Date goalDate, Date todayDate) throws ParseException {
        // SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        long milliseconds = goalDate.getTime() - todayDate.getTime();
        int days = (int) (milliseconds / (24 * 60 * 60 * 1000));
        return days;
    }
}