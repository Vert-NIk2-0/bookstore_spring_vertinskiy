package com.belhard.bookstore.dao.impl;

import com.belhard.bookstore.connection.impl.ConnectionManagerImpl;
import com.belhard.bookstore.dao.entity.Gender;
import com.belhard.bookstore.dao.entity.Role;
import com.belhard.bookstore.dao.UserDao;
import com.belhard.bookstore.dao.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
@RequiredArgsConstructor
@Repository
public class UserDaoImpl implements UserDao {

    private static final String SELECT_ALL_USERS =
            "SELECT u.id, u.first_name, u.last_name, u.email, u,date_of_birth, u.phone_number, g.gender, u.login, u.password, r.role " +
                    "FROM users u " +
                    "JOIN genders g ON u.role_id = g.id " +
                    "JOIN roles r ON u.role_id = r.id " +
                    "ORDER BY u.id";

    private static final String COUNT_ALL =
            "SELECT COUNT(*) AS row_count FROM users";

    private static final String INSERT_USER =
            "INSERT INTO users (first_name, last_name, email, date_of_birth, phone_number, gender_id, login, password)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    public static final String UPDATE_USER_NP =
            "UPDATE users SET " +
                "first_name = :first_name, " +
                "last_name = :last_name, " +
                "email = :email, " +
                "date_of_birth = :date_of_birth, " +
                "phone_number = :phone_number, " +
                "gender_id = :gender_id, " +
                "login = :login, " +
                "password = :password, " +
                "role_id = :role_id " +
            "WHERE id = :id";

    private static final String GET_GENDER_ID =
            "SELECT id FROM genders WHERE gender::text = ?";

    public static final String DELETE_USER_BY_ID =
            "DELETE FROM users WHERE id = ?";

    public static final String GET_USER_BY_ID =
            "SELECT u.id, u.first_name, u.last_name, u.email, u,date_of_birth, u.phone_number, g.gender, u.login, u.password, r.role " +
                    "FROM users u " +
                    "JOIN genders g ON u.gender_id = g.id " +
                    "JOIN roles r ON u.role_id = r.id " +
                    "WHERE u.id = ?";

    public static final String GET_USERS_BY_LASTNAME =
            "SELECT u.id, u.first_name, u.last_name, u.email, u,date_of_birth, u.phone_number, g.gender, u.login, u.password, r.role " +
                    "FROM users u " +
                    "JOIN genders g ON u.gender_id = g.id " +
                    "JOIN roles r ON u.role_id = r.id " +
                    "WHERE u.last_name = ?";

    public static final String GET_USER_BY_EMAIL =
            "SELECT u.id, u.first_name, u.last_name, u.email, u,date_of_birth, u.phone_number, g.gender, u.login, u.password, r.role " +
                    "FROM users u " +
                    "JOIN genders g ON u.gender_id = g.id " +
                    "JOIN roles r ON u.role_id = r.id " +
                    "WHERE u.email = ?";

    private static final String GET_ROLE_ID =
            "SELECT id FROM roles WHERE role::text = ?";


    private final ConnectionManagerImpl connectionManagerImpl;
    private final JdbcTemplate template;
    private final NamedParameterJdbcTemplate namedTemplate;

    @Override
    public User create(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update((connectionManagerImpl) -> getStatement(user), keyHolder);
        Long keyAs = keyHolder.getKeyAs(Long.class);
        return getById(keyAs);
    }

    @Override
    public User update(User user) {
        namedTemplate.update(UPDATE_USER_NP, getMap(user));
        return getById(user.getId());
    }

    @Override
    public List<User> getAll() {
        return template.query(SELECT_ALL_USERS, this::setBook);
    }

    @Override
    public List<User> getByLastName(String lastName) {
        return template.query(GET_USERS_BY_LASTNAME, this::setBook, lastName);
    }

    @Override
    public User getById(Long id) {
        return template.queryForObject(GET_USER_BY_ID, this::setBook, id);
    }

    @Override
    public User getByEmail(String email) {
        return template.queryForObject(GET_USER_BY_EMAIL, this::setBook, email);
    }

    @Override
    public boolean deleteById(Long id) {
        return template.update(DELETE_USER_BY_ID, id) == 1;
    }

    @Override
    public long countAll() {
        Long count = template.queryForObject(COUNT_ALL, ((rs, rowNum) -> rs.getLong("row_count")));
        return count != null ? count : 0L;

    }

    private Integer getGenderId (Gender gender) {
        return template.queryForObject(GET_GENDER_ID, ((rs, rowNum) -> rs.getInt("id")), String.valueOf(gender).toLowerCase());
    }

    private Integer getRoleId (Role role) {
        return template.queryForObject(GET_ROLE_ID, ((rs, rowNum) -> rs.getInt("id")), String.valueOf(role).toLowerCase());
    }

    private User setBook(ResultSet resultSet, int row){
        User user = new User();
        try {
            user.setId(resultSet.getLong("id"));
            user.setFirstName(resultSet.getString("first_name"));
            user.setLastName(resultSet.getString("last_name"));
            user.setEmail(resultSet.getString("email"));
            user.setDateOfBirth(resultSet.getDate("date_of_birth").toLocalDate());
            user.setPhoneNumber(resultSet.getString("phone_number"));
            user.setGender(Gender.valueOf(resultSet.getString("gender").toUpperCase()));
            user.setLogin(resultSet.getString("login"));
            user.setPassword(resultSet.getString("password"));
            user.setRole(Role.valueOf(resultSet.getString("role").toUpperCase()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    private PreparedStatement getStatement(User user) {
        try (
                Connection connection = connectionManagerImpl.getConnection();
                PreparedStatement statement = connection.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setDate(4, Date.valueOf(user.getDateOfBirth()));
            statement.setString(5, user.getPhoneNumber());
            statement.setInt(6, getGenderId(user.getGender()));
            statement.setString(7, user.getLogin());
            statement.setString(8, user.getPassword());
            return statement;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Map<String, Object> getMap(User user) {
        Map<String, Object> map = new HashMap<>();
        map.put("first_name", user.getFirstName());
        map.put("last_name", user.getLastName());
        map.put("email", user.getEmail());
        map.put("date_of_birth", Date.valueOf(user.getDateOfBirth()));
        map.put("phone_number", user.getPhoneNumber());
        map.put("gender_id", getGenderId(user.getGender()));
        map.put("login", user.getLogin());
        map.put("password", user.getPassword());
        map.put("role_id", getRoleId(user.getRole()));
        map.put("id", user.getId());
        return map;
    }
}
