package my.application.com.repository;

import my.application.com.model.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface GoalRepository extends JpaRepository<Goal, Long>, JpaSpecificationExecutor<Goal> {
    //    @Query("SELECT COUNT(ut) FROM goal ut WHERE ut.user_id = :id
    @Query("SELECT count(id) FROM Goal WHERE user_id= :id")
    Integer getGoalCountById(long id);
}
