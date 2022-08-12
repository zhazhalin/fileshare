package com.zhang.fileshare.service.impl;

import com.zhang.fileshare.entity.User;
import com.zhang.fileshare.dao.UserDao;
import com.zhang.fileshare.service.UserService;
import com.zhang.fileshare.utils.result.Result;
import com.zhang.fileshare.utils.token.JwtHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2022-08-10 15:48:34
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    @Override
    public User queryById(Long userId) {
        return this.userDao.queryById(userId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<User> queryAllByLimit(int offset, int limit) {
        return this.userDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User insert(User user) {
        this.userDao.insert(user);
        return user;
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User update(User user) {
        this.userDao.update(user);
        return this.queryById(user.getUserId());
    }

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long userId) {
        return this.userDao.deleteById(userId) > 0;
    }

    @Override
    public Result login(String username, String password) {
        User user=userDao.queryByUsername(username);
        if(user!=null&&user.getUserPwd().equals(password)){
            String token = JwtHelper.createToken(user.getUserId(), user.getUserName());
            Map<String,Object> map=new HashMap<>();
            map.put("token",token);
            return Result.ok(map);
        }else {
            return Result.fail("密码错误！");
        }
    }
}
