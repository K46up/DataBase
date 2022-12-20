package root.database.service;

import root.database.dao.DaoManager;
import root.database.model.User;

import java.util.List;

public class UserService {

    private DaoManager<User> daoManager;

    public UserService() {
        daoManager = new DaoManager<>(User.class);
    }

    public User findByUsername(String username) {
        for (var user : findAll()) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public User findById(int id) {
        return daoManager.findById(id);
    }

    public void save(User user) {
        daoManager.save(user);
    }

    public void update(User user) {
        daoManager.update(user);
    }

    public void delete(User user) {
        daoManager.delete(user);
    }

    public List<User> findAll() {
        return daoManager.findAll();
    }

}
