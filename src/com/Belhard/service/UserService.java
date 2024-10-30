package com.Belhard.service;

import com.Belhard.model.User;
import com.Belhard.model.UserDto;

import java.util.List;

public interface UserService {

    void createNewUser(UserDto dto);

    User login(String email, String password);
}
