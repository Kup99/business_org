package my.application.com.service;

import my.application.com.model.User;
import my.application.com.repository.GoalRepository;
import my.application.com.repository.RoleRepository;
import my.application.com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    GoalRepository goalRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public void saveAll(User user) {
        //user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public User findByUserName(String name) {
        //TODO:Show
        return userRepository.findByUserName(name);
    }

    @Override
    public List<User> getAllByLevel() {
        return userRepository.getAllByLevel();
    }

    @Override
    public Optional<User> findUserById(long id) {
        Optional<User> user = userRepository.findById(id);
        user.get().setGoalCount(goalRepository.getGoalCountById(id));
        return user;
    }

    @Override
    public void deleteUserById(long id) throws Exception {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new Exception("User is not exists");
        }
        userRepository.deleteById(id);
    }
}
