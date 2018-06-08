package com.jw.controller;


import com.jw.bean.User;
import com.jw.bean.User1;
import com.jw.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.subject.Subject;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/user")
@EnableAutoConfiguration
public class UserController {

    @Resource
    private UserService userService;


    /**
     * shiro的注解：
     * @RequiresAuthentication 需要进行用户认证
     * @RequiresUser 必须是系统的用户
     * @RequiresRoles({"role1", "role2"})
     * @RequiresPermissions({"p1", "p2"})
     * @RequiresGuest
     *
     * @return
     */


    @RequestMapping("runlogin")
    public ModelAndView runlogin(){

        return new ModelAndView("login");

    }

    @PostMapping("login")
    public String login(User1 user) {
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(new UsernamePasswordToken(user.getName(), user.getPassword()));
        } catch (AuthenticationException e) {
            System.out.println("用户登录失败");
        }
        return "redirect:index";
    }


    @RequestMapping("runsave")
    public ModelAndView runsave(){

        ModelAndView saveview = new ModelAndView("save");


        return saveview;
    }

    @RequestMapping("datasave")
    public ModelAndView datasave(User user){

        ModelAndView saveview = new ModelAndView("redirect:index");

        userService.save(user);


        return saveview;
    }


    @RequestMapping("dataupdate")
    public ModelAndView dataupdate(User user){



        userService.update(user);

        ModelAndView updateview = new ModelAndView("redirect:index");

        return updateview;
    }


    @RequestMapping("delete")
    public ModelAndView deleteById(Long id){

        userService.deleteById(id);

        ModelAndView deleteview =   new  ModelAndView("redirect:index");


        return deleteview;
    }

    @RequestMapping("findById/{id}")
    @RequiresRoles("用户")
    @RequiresPermissions("查询")
    public ModelAndView findbyid(@PathVariable Long id){

        ModelAndView findbyidview = new ModelAndView("update");



        User users = new User();



        users=userService.findById(id);


        findbyidview.addObject("user",users);





        return findbyidview;
    }


    @RequestMapping("index")
    @RequiresAuthentication
    public ModelAndView index(User user){

    ModelAndView view = new ModelAndView("index");



        Iterable<User> Userlist=null;


        Userlist=userService.findAll();

        view.addObject("userlist",Userlist);


      return view;
    }




}
