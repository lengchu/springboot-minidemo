package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
public class IndexController {
    @Autowired
    private UserRepository userDao;

    @GetMapping("/")
    @ResponseBody
    public String index() {
        return "<h1>Hello SpringBoot</h1>";
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @GetMapping("/db/save")
    @ResponseBody
    public String dbSave() {
        User u = new User();
        u.setUsername("lenchu");
        u.setPassword("lenchu");
        userDao.save(u);
        return "success";
    }

    @GetMapping("/db/get")
    @ResponseBody
    public User dbGet() {
        Optional<User> user = userDao.findById("lenchu");
        return user.get();
    }
}
