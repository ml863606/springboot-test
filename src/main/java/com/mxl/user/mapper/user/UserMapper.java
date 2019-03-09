package com.mxl.user.mapper.user;

import com.mxl.user.entity.UserModel;

import java.util.List;

public interface UserMapper {

    List<UserModel> findUserList();

    int deleteUserById(int userId);

    int updateUsersByBatch(List<UserModel> userModelList);
}
