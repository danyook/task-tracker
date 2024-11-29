package ru.kpfu.itis.services;

import ru.kpfu.itis.dao.TaskDAO;
import ru.kpfu.itis.dao.UserDAO;
import ru.kpfu.itis.entities.Section;
import ru.kpfu.itis.entities.User;
import ru.kpfu.itis.util.DbException;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class UserService {
    private static UserService INSTANCE;
    private final UserDAO userDAO = UserDAO.getInstance();

    private UserService() {
    }

    public static UserService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserService();
        }
        return INSTANCE;
    }

    public List<User> findAll() {
        return userDAO.findAll();
    }

    public User findOne(int id) {
        return userDAO.findById(id);
    }

    public List<User> findByTeamId(int teamId) {
        if (userDAO.findByTeamId(teamId) == null) {
            return Collections.emptyList();
        }
        return userDAO.findByTeamId(teamId);
    }

    public void save(User section) {
        userDAO.save(section);
    }

    public void update(int id, User updatedUser) {
        userDAO.update(id, updatedUser);
    }

    public void delete(int id) {
        userDAO.delete(id);
    }

    public User findByUsername(String username) {
        return userDAO.findByUsername(username);
    }
}
