package com.czx.bookproject.service.impl;

import com.czx.bookproject.Bean.User;
import com.czx.bookproject.mapper.UserMapper;
import com.czx.bookproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public boolean isCorrect(String username, String password) {
        User user = userMapper.selectUserByUsernameAndPassword(username, password);
        return user != null;
    }

    @Override
    public boolean isExistUsername(String username) {
        String name = userMapper.selectUsername(username);
        return name!=null;
    }

    @Override
    public int addUser(User user) {
        return userMapper.insertUser(user.getUsername(), user.getPassword(), user.getEmail());
    }
}
