package com.Belhard.dao;

import com.Belhard.model.User;

import java.util.List;

public interface UserDao {
    void createUser(User user);

    User updateUser(User user);

    List<User> getAllUsers();

    List<User> getUsersByLastName(String lastName);

    User getUserById(Integer id);

    User getUserByEmail(String email);

    boolean deleteUserById(Integer id);

    Long countAll();
}
