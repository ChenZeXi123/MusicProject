package com.czx.bookproject.service;

import com.czx.bookproject.Bean.User;

public interface UserService {
    //登录时判断用户名和密码
    boolean isCorrect(String username,String password);

    //判断注册的用户名是否存在
    boolean isExistUsername(String username);

    //添加用户
    int addUser(User user);
}
