package com.bjsxt.springbootjdbc.controller;

import com.bjsxt.springbootjdbc.pojo.Users;
import com.bjsxt.springbootjdbc.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.sql.DataSource;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UsersController {

    @Autowired
    private UsersService usersService;

    /**
     * 添加用户
     * @return
     */
    @PostMapping("/addUser")
    public String showInfo(Users users){
        try{
            this.usersService.addUser(users);
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
        //防止表单重复提交，需要选择重定向模式
        return "redirect:/ok";
    }

    /**
     * 查询全部用户
     */
    @GetMapping("/findUserAll")
    public String findUserAll(Model model){
        List<Users> list = null;
        try{
            list = this.usersService.findUsersAll();
            model.addAttribute("list",list);
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
        //不可使用重定向，只能使用请求转发
        return "showUsers";
    }

    /**
     * 预更新用户的查询
     */
    @GetMapping("/preUpdateUsers")
    public String preUpdateUsers(Integer id,Model model){
        try{
            Users user = this.usersService.findUserById(id);
            model.addAttribute("user",user);
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
        return "updateUser";
    }

    /**
     * 更新用户
     */
    @PostMapping("/updateUser")
    public String updateUser(Users users){
        try{
            this.usersService.modifyUser(users);
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
        return "redirect:/ok";
    }

    /**
     * 删除用户
     */
    @GetMapping("/deleteUsers")
    @Transactional
    public String deleteUser(Integer id){
        try{
            this.usersService.dropUser(id);
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
        return "redirect:/ok";
    }
}
