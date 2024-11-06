package com.Belhard.bookstore.dao;

import com.Belhard.bookstore.model.User;

import java.util.List;

public interface UserDao {
    void createUser(User user);

    User updateUser(User user);

    List<User> getAllUsers();

    List<User> getUsersByLastName(String lastName);

    User getUserById(Long id);

    User getUserByEmail(String email);

    boolean deleteUserById(Long id);

    Long countAll();
}
