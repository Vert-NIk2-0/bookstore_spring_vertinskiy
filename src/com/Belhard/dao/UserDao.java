package com.Belhard.dao;

import com.Belhard.model.Book;
import com.Belhard.model.User;

import java.util.List;

public interface UserDao {
    void createUser(User book);

    User updateUser(User book);

    List<User> getAllUsers();

    List<User> getUsersByLastName(String lastName);

    User getUserById(Long id);

    User getUserByEmail(String email);

    boolean deleteUserById(Long id);

    Long countAll();
}
