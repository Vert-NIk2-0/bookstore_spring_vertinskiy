package com.Belhard.dao;

import com.Belhard.enums.Gender;
import com.Belhard.enums.Role;
import com.Belhard.implementations.Implementation;
import com.Belhard.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserImpl extends Implementation implements UserDao{

    private static final String SELECT_ALL_USERS =
            "SELECT u.id, u.first_name, u.last_name, u.email, u,date_of_birth, u.phone_number, g.gender, u.login, u.password, r.role " +
                    "FROM users u " +
                    "JOIN genders g ON u.role_id = g.id " +
                    "JOIN roles r ON u.role_id = r.id " +
                    "ORDER BY u.id";

    private static final String USERS_SIZE =
            "SELECT COUNT(*) AS row_count FROM users";

    private static final String INSERT_USER =
            "INSERT INTO users (first_name, last_name, email, date_of_birth, phone_number, gender_id, login, password)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    public static final String UPDATE_USER =
            "UPDATE users " +
                    "SET first_name = ?, last_name = ?, email = ?, date_of_birth = ?, phone_number = ?, gender_id = ?, login = ?, password = ?, role_id = ? WHERE id = ?";

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

    @Override
    public void createUser(User user) {
        try (PreparedStatement statement = getConnection().prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS)) {
            setStatement(user, statement);

            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            generatedKeys.next();
            user.setId(generatedKeys.getLong("id"));

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User updateUser(User user) {
        try (PreparedStatement statement = getConnection().prepareStatement(UPDATE_USER)) {

            setStatement(user, statement);

            statement.setInt(9, getRoleId(user.getRole()));
            statement.setLong(10, user.getId());

            statement.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Statement statement = getConnection().createStatement()){
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_USERS);
            while (resultSet.next()) {
                User user = new User();

                setBook(user, resultSet);

                userList.add(user);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }

    @Override
    public List<User> getUsersByLastName(String lastName) {
        List<User> userList = new ArrayList<>();
        try (PreparedStatement statement = getConnection().prepareStatement(GET_USERS_BY_LASTNAME)) {
            statement.setString(1, lastName);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                User user = new User();
                setBook(user, resultSet);
                userList.add(user);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return userList;
    }

    @Override
    public User getUserById(Integer id) {
        User user = new User();
        try (PreparedStatement statement = getConnection().prepareStatement(GET_USER_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {

                setBook(user, resultSet);

            } else {
                return null;
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        User user = new User();
        try (PreparedStatement statement = getConnection().prepareStatement(GET_USER_BY_EMAIL)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {

                setBook(user, resultSet);

            } else {
                return null;
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public boolean deleteUserById(Integer id) {
        try (PreparedStatement statement = getConnection().prepareStatement(DELETE_USER_BY_ID)) {
            statement.setInt(1, id);
            return statement.executeUpdate() == 1;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Long countAll() {
        long rowCount = 0L;
        try (Statement statement = getConnection().createStatement()){
            ResultSet resultSet = statement.executeQuery(USERS_SIZE);
            if (resultSet.next())
                rowCount = resultSet.getLong("row_count");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rowCount;
    }

    private Integer getGenderId (Gender gender) {
        try (PreparedStatement statement = getConnection().prepareStatement(GET_GENDER_ID)){
            statement.setString(1, String.valueOf(gender));
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            return resultSet.getInt("id");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Integer getRoleId (Role role) {
        try (PreparedStatement statement = getConnection().prepareStatement(GET_ROLE_ID)){
            statement.setString(1, String.valueOf(role));
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            return resultSet.getInt("id");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setBook(User user, ResultSet resultSet) throws SQLException {
        user.setId(resultSet.getLong("id"));
        user.setFirstName(resultSet.getString("first_name"));
        user.setLastName(resultSet.getString("last_name"));
        user.setEmail(resultSet.getString("email"));
        user.setDateOfBirth(resultSet.getDate("date_of_birth"));
        user.setPhoneNumber(resultSet.getString("phone_number"));
        user.setGender(Gender.valueOf(resultSet.getString("gender")));
        user.setLogin(resultSet.getString("login"));
        user.setPassword(resultSet.getString("password"));
        user.setRole(Role.valueOf(resultSet.getString("role")));
    }

    private void setStatement(User user, PreparedStatement statement) throws SQLException {
        statement.setString(1, user.getFirstName());
        statement.setString(2, user.getLastName());
        statement.setString(3, user.getEmail());
        statement.setDate(4, user.getDateOfBirth());
        statement.setString(5, user.getPhoneNumber());
        statement.setInt(6, getGenderId(user.getGender()));
        statement.setString(7, user.getLogin());
        statement.setString(8, user.getPassword());
    }
}
