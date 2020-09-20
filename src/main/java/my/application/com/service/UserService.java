package my.application.com.service;

import my.application.com.model.User;

import java.util.List;

public interface UserService {
    void saveAll(User user);

    User findByUserName(String name);

    boolean userAlreadyExists(String name);

    List<User> getAll();

}
