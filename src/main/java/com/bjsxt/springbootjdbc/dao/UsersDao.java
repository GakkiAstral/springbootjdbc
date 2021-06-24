package com.bjsxt.springbootjdbc.dao;

import com.bjsxt.springbootjdbc.pojo.Users;

import java.util.List;

public interface UsersDao {
    void insertUsers(Users users);

    List<Users> selectUsersAll();

    Users selectUserById(Integer id);

    void updateUser(Users users);

    void deleteUserById(Integer id);
}
