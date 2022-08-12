package com.zhang.fileshare.service;

import com.zhang.fileshare.entity.Myfile;
import com.zhang.fileshare.utils.result.Result;

import java.util.List;

/**
 * (Myfile)表服务接口
 *
 * @author makejava
 * @since 2022-08-11 16:04:11
 */
public interface MyfileService {

    /**
     * 通过ID查询单条数据
     *
     * @param fileId 主键
     * @return 实例对象
     */
    Myfile queryById(Long fileId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Myfile> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param myfile 实例对象
     * @return 实例对象
     */
    Myfile insert(Myfile myfile);

    /**
     * 修改数据
     *
     * @param myfile 实例对象
     * @return 实例对象
     */
    Myfile update(Myfile myfile);

    /**
     * 通过主键删除数据
     *
     * @param fileId 主键
     * @return 是否成功
     */
    boolean deleteById(Long fileId);

    Result queryByType(String type);
}
