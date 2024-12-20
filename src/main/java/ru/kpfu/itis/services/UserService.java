package ru.kpfu.itis.services;

import ru.kpfu.itis.dao.AvatarDAO;
import ru.kpfu.itis.dao.UserDAO;
import ru.kpfu.itis.entities.User;
import ru.kpfu.itis.exception.NotCorrectPasswordException;
import ru.kpfu.itis.exception.NotFoundUserException;
import ru.kpfu.itis.exception.OldPasswordDoesNotMatchException;
import ru.kpfu.itis.util.PassEncrypt;

import java.util.Collections;
import java.util.List;

public class UserService {
    private static UserService INSTANCE;
    private final UserDAO userDAO = UserDAO.getInstance();
    private final AvatarDAO avatarDAO = AvatarDAO.getInstance();

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

    public User findById(int id) {
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

    public boolean checkPass(String username, String pass) {
        User user = userDAO.findByUsername(username);
        if (user == null) {
            throw new NotFoundUserException("User with this username not found");
        }

        String truePass = user.getPassword();

        return (pass.equals(truePass));
    }

    public void updatePassword(int userId, String oldPass, String newPassOne, String newPassTwo) {
        User user = userDAO.findById(userId);
        if (user == null) {
            throw new NotFoundUserException("Пользователь с таким id не найден");
        }

        if (!user.getPassword().equals(oldPass)) {
            throw new OldPasswordDoesNotMatchException("Старый пароль не совпадает");
        }

        if (!newPassOne.equals(newPassTwo)) {
            throw new NotCorrectPasswordException("Новые пароли не совпадают");
        }

        userDAO.updatePassword(userId, newPassOne);
    }


    public void uploadPhoto(int userId, String url) {
        if (avatarDAO.findUrlByUserId(userId) == null) avatarDAO.save(userId, url);
        else avatarDAO.update(userId, url);
    }


    public String getPhotoUrl(int userId) {
        String url = avatarDAO.findUrlByUserId(userId);
        if (url == null) return "/static/defaultAvatar.jpg";
        return url;
    }


    public void deletePhoto(int userId) {
        avatarDAO.delete(userId);
    }

    public boolean checkUsername(String username) {
        User user = userDAO.findByUsername(username);
        if (user == null) {
            return true;
        } else {
            return false;
        }
    }

}
