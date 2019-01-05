package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class UserController {
    private final UserRepository userDao;

    public UserController(UserRepository userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/user/add")
    public String addView() {
        return "reg";
    }

    @PostMapping("/user/add")
    public String add(@RequestParam String username, @RequestParam String password) {
        if (username == null || password == null || "".equals(username) || "".equals(password)) {
            return "用户名或密码不能为空";
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        userDao.save(user);

        return "redirect:/user/login";
    }

    @GetMapping("/user/login")
    public String loginView() {
        return "login";
    }

    @PostMapping("/user/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session) {
        if (username == null || password == null || "".equals(username) || "".equals(password)) {
            return "用户名或密码不能为空";
        }

        User user = userDao.findByUsernameAndPassword(username, password).orElse(null);
        session.setAttribute("user", user);
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String home(Map<String, Object> map, HttpSession session) {
        map.put("user", session.getAttribute("user"));
        return "index";
    }
}
