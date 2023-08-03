package com.iris.demo02;

import com.iris.demo04.UserService;
import com.iris.demo04.UserServiceImpl;

import java.util.Date;

public class UserServiceProxy implements UserService {
    private UserServiceImpl userService;

    public UserServiceProxy() {
        this.userService = new UserServiceImpl();
    }
    private void log(String msg) {
        System.out.println("Do " + msg + " " + new Date( ));
    }

    @Override
    public void add() {
        log("add");
        userService.add();
    }

    @Override
    public void delete() {
        log("delete");
        userService.delete();
    }

    @Override
    public void update() {
        log("update");
        userService.update();
    }

    @Override
    public void query() {
        log("query");
        userService.query();
    }
}
