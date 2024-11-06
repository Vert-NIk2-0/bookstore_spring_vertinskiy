package com.belhard.bookstore.service.impl;

import com.belhard.bookstore.dao.UserDao;
import com.belhard.bookstore.dao.entity.User;
import com.belhard.bookstore.service.entity.UserDto;
import com.belhard.bookstore.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserDtoImpl implements UserService {

    private final UserDao userDao;
    private static final Logger logger = LogManager.getLogger(UserDtoImpl.class);

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

        userDao.create(user);
        logger.debug("A new user has been created");
    }

    @Override
    public User login(String email, String password) {
        try {
            User user = userDao.getByEmail(email);
            if (user != null && user.getPassword().equals(password)) {
                logger.debug("User is logged in");
                return user;
            }

        }catch (RuntimeException e) {
            logger.error("Login error: email does not exist");
            System.out.println("This email does not exist");
        }

        return null;
    }
}
