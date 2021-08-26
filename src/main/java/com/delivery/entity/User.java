package com.delivery.entity;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
    private int id;
    private String login;
    private String name;
    private String secondName;
    private String password;
    private String email;
    private String phone;
    private String role;

    public User(int id,String login) {
        this.id=id;
        this.login = login;
    }

    public User(int id, String login, String name, String secondName, String password, String email, String phone, String role) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.secondName = secondName;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(login, user.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login);
    }

    public static User createUser(String login,String name, String secondName, String password, String email,String phone) {
        return new User(0,login, name,  secondName,  password,  email,phone, Role.CLIENT);
    }

    public String getPhone() {
        return phone;
    }

    public String getRole() {
        return role;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", secondName='" + secondName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
    public static class Role{
        public static String MANAGER="manager";
        public static String CLIENT="client";
    }
}
