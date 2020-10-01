package my.application.com.repository;

import my.application.com.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    @Query("SELECT u FROM User u WHERE u.userName= :name")
    User findByUserName(String name);

    @Query(
            value = "SELECT * FROM User order by level",
            nativeQuery = true)
    List<User> getAllByLevel();


}