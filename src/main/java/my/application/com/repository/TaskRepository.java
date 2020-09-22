package my.application.com.repository;

import my.application.com.model.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TaskRepository extends JpaRepository<Goal, Long>, JpaSpecificationExecutor<Goal> {

}
