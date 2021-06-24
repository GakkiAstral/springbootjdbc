package com.bjsxt.springbootjdbc.service;

import com.bjsxt.springbootjdbc.pojo.Users;

import java.util.List;

public interface UsersService {
    void addUser(Users users);

    List<Users> findUsersAll();

    Users findUserById(Integer id);

    void modifyUser(Users users);

    void dropUser(Integer id);
}
