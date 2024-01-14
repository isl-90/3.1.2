package kata.project1.project1.dao;

import kata.project1.project1.model.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();

    User getUserById(int id);

    void addUser(User user);

    void updateUser(int id, User updateUser);

    void removeUser(int id);
}