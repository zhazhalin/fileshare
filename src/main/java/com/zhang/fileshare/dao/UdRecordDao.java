package com.zhang.fileshare.dao;

import com.zhang.fileshare.entity.UdRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (UdRecord)表数据库访问层
 *
 * @author makejava
 * @since 2022-08-11 16:06:03
 */
@Mapper
public interface UdRecordDao {

    /**
     * 通过ID查询单条数据
     *
     * @param udId 主键
     * @return 实例对象
     */
    UdRecord queryById(Long udId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<UdRecord> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param udRecord 实例对象
     * @return 对象列表
     */
    List<UdRecord> queryAll(UdRecord udRecord);

    /**
     * 新增数据
     *
     * @param udRecord 实例对象
     * @return 影响行数
     */
    int insert(UdRecord udRecord);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<UdRecord> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<UdRecord> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<UdRecord> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<UdRecord> entities);

    /**
     * 修改数据
     *
     * @param udRecord 实例对象
     * @return 影响行数
     */
    int update(UdRecord udRecord);

    /**
     * 通过主键删除数据
     *
     * @param udId 主键
     * @return 影响行数
     */
    int deleteById(Long udId);

}

