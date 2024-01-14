package kata.project1.project1.service;

import kata.project1.project1.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUserById(int id);

    void addUser(User user);

    void updateUser(int id, User updateUser);

    void removeUser(int id);
}