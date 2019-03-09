package com.mxl.user.service.impl;

import com.mxl.user.mapper.user.UserMapper;
import com.mxl.user.entity.UserModel;
import com.mxl.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public List<UserModel> findUserList() {
        return userMapper.findUserList();
    }

    @Override
    public int updateUsersByBatch(List<UserModel> userModelList) {
        return userMapper.updateUsersByBatch(userModelList);
    }

    @Override
    public int deleteUserById(int userId) {
        return userMapper.deleteUserById(userId);
    }
}
