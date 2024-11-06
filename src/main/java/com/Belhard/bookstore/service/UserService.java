package com.belhard.bookstore.service;

import com.belhard.bookstore.dao.entity.User;
import com.belhard.bookstore.service.entity.UserDto;

public interface UserService {

    void createNewUser(UserDto dto);

    User login(String email, String password);
}
