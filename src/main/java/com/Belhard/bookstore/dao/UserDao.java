package com.belhard.bookstore.dao;

import com.belhard.bookstore.dao.entity.User;

import java.util.List;

public interface UserDao {
    void create(User user);

    User update(User user);

    List<User> getAll();

    List<User> getByLastName(String lastName);

    User getById(Long id);

    User getByEmail(String email);

    boolean deleteById(Long id);

    long countAll();
}
