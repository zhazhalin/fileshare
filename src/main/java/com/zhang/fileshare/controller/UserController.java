package com.zhang.fileshare.controller;

import com.zhang.fileshare.entity.User;
import com.zhang.fileshare.service.UserService;
import com.zhang.fileshare.utils.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (User)表控制层
 *
 * @author makejava
 * @since 2022-08-10 15:48:34
 */
@RestController
@RequestMapping("/user")
@Api("用户操作")
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;

    /**
     * 用户注册
     * @param user
     * @return
     */
    @ApiOperation("用户注册")
    @PostMapping("/register")
    public Result insert(@RequestBody User user) {
        this.userService.insert(user);
        return Result.ok();
    }

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    @ApiOperation("用户登录")
    @GetMapping("/login/{username}/{password}")
    public Result login(@PathVariable String username, @PathVariable String password) {
        Result result = this.userService.login(username, password);
        return result;
    }

}
