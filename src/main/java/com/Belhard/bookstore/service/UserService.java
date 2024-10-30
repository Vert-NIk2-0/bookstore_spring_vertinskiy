package com.Belhard.bookstore.service;

import com.Belhard.bookstore.model.User;
import com.Belhard.bookstore.model.UserDto;

public interface UserService {

    void createNewUser(UserDto dto);

    User login(String email, String password);
}
