package com.zhang.fileshare.dao;

import com.zhang.fileshare.entity.Myfile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Myfile)表数据库访问层
 *
 * @author makejava
 * @since 2022-08-11 16:04:11
 */
@Mapper
public interface MyfileDao {

    /**
     * 通过ID查询单条数据
     *
     * @param fileId 主键
     * @return 实例对象
     */
    Myfile queryById(Long fileId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Myfile> queryAllByLimit(Myfile myfile,@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param myfile 实例对象
     * @return 对象列表
     */
    List<Myfile> queryAll(Myfile myfile);

    /**
     * 新增数据
     *
     * @param myfile 实例对象
     * @return 影响行数
     */
    int insert(Myfile myfile);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Myfile> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Myfile> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Myfile> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<Myfile> entities);

    /**
     * 修改数据
     *
     * @param myfile 实例对象
     * @return 影响行数
     */
    int update(Myfile myfile);

    /**
     * 通过主键删除数据
     *
     * @param fileId 主键
     * @return 影响行数
     */
    int deleteById(Long fileId);

}

