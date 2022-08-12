package com.zhang.fileshare.service;

import com.zhang.fileshare.entity.UdRecord;
import com.zhang.fileshare.utils.result.Result;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * (UdRecord)表服务接口
 *
 * @author makejava
 * @since 2022-08-11 16:06:04
 */
public interface UdRecordService {

    /**
     * 通过ID查询单条数据
     *
     * @param udId 主键
     * @return 实例对象
     */
    UdRecord queryById(Long udId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<UdRecord> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param udRecord 实例对象
     * @return 实例对象
     */
    UdRecord insert(UdRecord udRecord);

    /**
     * 修改数据
     *
     * @param udRecord 实例对象
     * @return 实例对象
     */
    UdRecord update(UdRecord udRecord);

    /**
     * 通过主键删除数据
     *
     * @param udId 主键
     * @return 是否成功
     */
    boolean deleteById(Long udId);

    Result upload(HttpServletRequest request,String isShare,String description,MultipartFile file);
}
