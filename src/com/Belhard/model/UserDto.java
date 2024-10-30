package com.Belhard.model;

import com.Belhard.enums.Gender;

import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.Objects;

public class UserDto {
    private Long id;
    private String login;
    private String password;
    private String email;
    private Date dateOfBirth;
    private Gender gender;
    private final SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(id, userDto.id) && Objects.equals(login, userDto.login) && Objects.equals(password, userDto.password) && Objects.equals(email, userDto.email) && Objects.equals(dateOfBirth, userDto.dateOfBirth) && gender == userDto.gender;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, email, dateOfBirth, gender);
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", dateOfBirth=" + formatter.format(dateOfBirth) +
                ", gender=" + gender +
                '}';
    }
}
