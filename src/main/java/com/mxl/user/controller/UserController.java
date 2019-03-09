package com.mxl.user.controller;

import com.mxl.user.entity.UserModel;
import com.mxl.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试控制层
 *
 * @author zpy
 * @since 2018-12-29
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("")
    public String test() {
        return "running......";
    }

    @RequestMapping("/findUserList")
    @ResponseBody
    public List<UserModel> findUserList() {
        List<UserModel> userList = userService.findUserList();
        return userList;
    }


    @RequestMapping("/updateUsersByBatch")
    @ResponseBody
    public String updateUsersByBatch() {
        List<UserModel> userList = new ArrayList<UserModel>();
        for (int i = 1; i < 4; i++) {
            UserModel user = new UserModel(i, "小马" + i, 10 + i);
            userList.add(user);
        }
        int resultRows = userService.updateUsersByBatch(userList);

        if (resultRows > 0) {
            return "批量更新成功: " + resultRows + "条";
        } else {
            return "批量更新成功: " + resultRows + "条";
        }
    }
}
