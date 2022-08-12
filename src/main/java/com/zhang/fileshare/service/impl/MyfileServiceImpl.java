package com.zhang.fileshare.service.impl;

import com.zhang.fileshare.entity.Myfile;
import com.zhang.fileshare.dao.MyfileDao;
import com.zhang.fileshare.service.MyfileService;
import com.zhang.fileshare.utils.result.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Myfile)表服务实现类
 *
 * @author makejava
 * @since 2022-08-11 16:04:12
 */
@Service("myfileService")
public class MyfileServiceImpl implements MyfileService {
    @Resource
    private MyfileDao myfileDao;

    /**
     * 通过ID查询单条数据
     *
     * @param fileId 主键
     * @return 实例对象
     */
    @Override
    public Myfile queryById(Long fileId) {
        return this.myfileDao.queryById(fileId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Myfile> queryAllByLimit(int offset, int limit) {
        return this.myfileDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param myfile 实例对象
     * @return 实例对象
     */
    @Override
    public Myfile insert(Myfile myfile) {
        this.myfileDao.insert(myfile);
        return myfile;
    }

    /**
     * 修改数据
     *
     * @param myfile 实例对象
     * @return 实例对象
     */
    @Override
    public Myfile update(Myfile myfile) {
        this.myfileDao.update(myfile);
        return this.queryById(myfile.getFileId());
    }

    /**
     * 通过主键删除数据
     *
     * @param fileId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long fileId) {
        return this.myfileDao.deleteById(fileId) > 0;
    }

    @Override
    public Result queryByType(String type) {

        return Result.ok();
    }
}
