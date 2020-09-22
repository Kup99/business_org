package my.application.com.service;

import my.application.com.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void saveAll(User user);

    User findByUserName(String name);

    List<User> getAll();

    void deleteUserById(long id) throws Exception;

    public Optional<User> findUserById(long id);

}
