package com.mxl.user.service;

import com.mxl.user.entity.UserModel;

import java.util.List;

public interface IUserService {
    List<UserModel> findUserList();

    int updateUsersByBatch(List<UserModel> userModelList);

    int deleteUserById(int userId);


}
