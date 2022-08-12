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
    public List<Myfile> queryAllByLimit(Myfile myfile,int offset, int limit) {
        return this.myfileDao.queryAllByLimit(myfile,offset, limit);
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

    /**
     * 根据文件类型进行分类搜索
     * @param type
     * @return
     */
    @Override
    public Result<List<Myfile>> queryByType(String type) {
        Myfile myfile=new Myfile();
        myfile.setFileContenttype(type);//根据文件类型搜索
        myfile.setIsShare("1");//搜寻公开文件
        //根据类型查询出所有文件
        List<Myfile> typeAllFile=myfileDao.queryAll(myfile);
        return Result.ok(typeAllFile);
    }

    @Override
    public List<Myfile> searchFile(String fileName) {
        //根据文件名称进行模糊查询(查询所有公开文件)
        Myfile myfile=new Myfile();
        myfile.setIsShare("1");
        myfile.setFileName(fileName);
        List<Myfile> myfiles=myfileDao.queryAll(myfile);
        return myfiles;
    }
}
