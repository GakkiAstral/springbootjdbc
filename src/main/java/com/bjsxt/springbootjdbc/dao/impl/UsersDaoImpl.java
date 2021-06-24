package com.bjsxt.springbootjdbc.dao.impl;

import com.bjsxt.springbootjdbc.dao.UsersDao;
import com.bjsxt.springbootjdbc.pojo.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * 用户管理持久层
 */
@Repository
public class UsersDaoImpl implements UsersDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 添加用户
     * @param users
     */
    @Override
    public void insertUsers(Users users) {
        String sql = "insert into users(username,usersex) values(?,?)";
        this.jdbcTemplate.update(sql,users.getUsername(),users.getUsersex());
    }

    /**
     * 查询全部用户
     * @return
     */
    @Override
    public List<Users> selectUsersAll() {
        String sql = "select * from users";

        return this.jdbcTemplate.query(sql, new RowMapper<Users>() {
            /**
             * 结果集的映射
             * @param resultSet
             * @param i
             * @return
             * @throws SQLException
             */
            @Override
            public Users mapRow(ResultSet resultSet, int i) throws SQLException {
                Users users = new Users();
                users.setUserid(resultSet.getInt("userid"));
                users.setUsername(resultSet.getString("username"));
                users.setUsersex(resultSet.getString("usersex"));
                return users;
            }
        });
    }

    /**
     * 预更新用户查询
     * @param id
     * @return
     */
    @Override
    public Users selectUserById(Integer id) {
        Users users = new Users();
        String sql = "select * from users where userid = ?";
        Object[] arr = new Object[]{id};
        this.jdbcTemplate.query(sql, arr, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                users.setUserid(resultSet.getInt("userid"));
                users.setUsername(resultSet.getString("username"));
                users.setUsersex(resultSet.getString("usersex"));
            }
        });
        return users;
    }

    /**
     * 更新用户
     * @param users
     */
    @Override
    public void updateUser(Users users) {
        String sql = "update users set username = ?,usersex = ? where userid = ?";
        this.jdbcTemplate.update(sql,users.getUsername(),users.getUsersex(),users.getUserid());
    }

    /**
     * 删除用户
     * @param id
     */
    @Override
    public void deleteUserById(Integer id) {
        String sql = "delete from users where userid = ?";
        this.jdbcTemplate.update(sql,id);
    }
}
