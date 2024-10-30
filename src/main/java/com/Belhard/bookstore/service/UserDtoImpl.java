package com.Belhard.bookstore.service;

import com.Belhard.bookstore.dao.UserDao;
import com.Belhard.bookstore.implementations.Implementation;
import com.Belhard.bookstore.model.User;
import com.Belhard.bookstore.model.UserDto;

public class UserDtoImpl extends Implementation implements UserService{

    private UserDao userDao;

    public UserDtoImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void createNewUser(UserDto dto) {
        User user = new User();

        user.setId(dto.getId());
        user.setLogin(dto.getLogin());
        user.setPassword(dto.getPassword());
        user.setEmail(dto.getEmail());
        user.setDateOfBirth(dto.getDateOfBirth());
        user.setGender(dto.getGender());

        userDao.createUser(user);
    }

    @Override
    public User login(String email, String password) {
        try {
            User user = userDao.getUserByEmail(email);
            if (user != null && user.getPassword().equals(password)) {
                return user;
            }

        }catch (RuntimeException e) {
            System.out.println("This email already exists");
        }

        return null;
    }
}
