package my.application.com.service;

import my.application.com.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void saveAll(User user);

    User findByUserName(String name);

    List<User> getAllByLevel();

    void deleteUserById(long id) throws Exception;

    Optional<User> findUserById(long id);

}
