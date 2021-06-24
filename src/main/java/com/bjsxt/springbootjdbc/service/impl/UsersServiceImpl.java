package com.bjsxt.springbootjdbc.service.impl;

import com.bjsxt.springbootjdbc.dao.UsersDao;
import com.bjsxt.springbootjdbc.pojo.Users;
import com.bjsxt.springbootjdbc.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户管理业务层
 */
@Service
public class UsersServiceImpl implements UsersService{

    @Autowired
    private UsersDao usersDao;

    /**
     * 添加用户
     * @param users
     */
    @Override
    @Transactional
    public void addUser(Users users) {
        this.usersDao.insertUsers(users);
    }

    /**
     * 查询全部用户
     * @return
     */
    @Override
    //查询工作不需要事务辅助，所以就不需要加@Transactional注解了
    public List<Users> findUsersAll() {
        return this.usersDao.selectUsersAll();
    }

    /**
     * 预更新查询
     * @param id
     * @return
     */
    @Override
    public Users findUserById(Integer id) {
        return this.usersDao.selectUserById(id);
    }

    /**
     * 更新用户
     * @param users
     */
    @Override
    @Transactional
    public void modifyUser(Users users) {
        this.usersDao.updateUser(users);
    }

    /**
     * 删除用户
     * @param id
     */
    @Override
    public void dropUser(Integer id) {
        this.usersDao.deleteUserById(id);
    }
}
