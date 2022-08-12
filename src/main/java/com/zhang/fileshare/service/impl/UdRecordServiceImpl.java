package com.zhang.fileshare.service.impl;

import com.zhang.fileshare.dao.UdRecordDao;
import com.zhang.fileshare.entity.Myfile;
import com.zhang.fileshare.entity.UdRecord;
import com.zhang.fileshare.service.MyfileService;
import com.zhang.fileshare.service.UdRecordService;
import com.zhang.fileshare.utils.oss.OssUtils;
import com.zhang.fileshare.utils.result.Result;
import com.zhang.fileshare.utils.result.ResultCodeEnum;
import com.zhang.fileshare.utils.token.JwtHelper;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * (UdRecord)表服务实现类
 *
 * @author makejava
 * @since 2022-08-11 16:06:04
 */
@Service("udRecordService")
public class UdRecordServiceImpl implements UdRecordService {
    @Resource
    private UdRecordDao udRecordDao;
    @Autowired
    private MyfileService myfileService;

    /**
     * 通过ID查询单条数据
     *
     * @param udId 主键
     * @return 实例对象
     */
    @Override
    public UdRecord queryById(Long udId) {
        return this.udRecordDao.queryById(udId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<UdRecord> queryAllByLimit(int offset, int limit) {
        return this.udRecordDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param udRecord 实例对象
     * @return 实例对象
     */
    @Override
    public UdRecord insert(UdRecord udRecord) {
        this.udRecordDao.insert(udRecord);
        return udRecord;
    }

    /**
     * 修改数据
     *
     * @param udRecord 实例对象
     * @return 实例对象
     */
    @Override
    public UdRecord update(UdRecord udRecord) {
        this.udRecordDao.update(udRecord);
        return this.queryById(udRecord.getUdId());
    }

    /**
     * 通过主键删除数据
     *
     * @param udId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long udId) {
        return this.udRecordDao.deleteById(udId) > 0;
    }

    @Override
    public Result upload(HttpServletRequest request,String isShare, String description,MultipartFile file) {
        String fileUrl = OssUtils.upload(request, file);
        if(fileUrl==null){
            return Result.fail("上传失败，请重试！");
        }
        //获取文件类型
        String fileType = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String type = fileType.substring(1);
        Myfile myfile=new Myfile();
        //设置文件创建时间
        myfile.setCreatetime(DateTime.now().toDate());
        //文件类型
        myfile.setFileContenttype(type);
        //文件名称
        myfile.setFileName(file.getOriginalFilename());
        //文件路径
        myfile.setFileUrl(fileUrl);
        //文件所有者
        myfile.setFileUser(JwtHelper.getUserId(request.getHeader("token")));
        //是否公开
        myfile.setIsShare(isShare);
        //文件描述
        myfile.setDescription(description);
        //上传还是下载
        myfile.setFileSize(file.getSize());
        //是否删除
        myfile.setIsDeleted("0");
        //下载次数
        myfile.setDowloadCount(0);
        Myfile insert=null;
        try {
            //将文件信息添加到数据库
            insert = myfileService.insert(myfile);
            if(insert!=null){
                Result.ok(ResultCodeEnum.UPLOAD_SUCCESS);//上传成功
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(insert!=null){
                UdRecord udRecord=new UdRecord();
                udRecord.setUserId(JwtHelper.getUserId(request.getHeader("token")));
                udRecord.setCreatetime(new DateTime().toDate());
                udRecord.setFileId(insert.getFileId());
                udRecord.setIsShare(isShare); //设置是否公开
                udRecord.setOption(0);//设置操作类型为上传
                udRecordDao.insert(udRecord);
            }
        }
        return Result.ok(ResultCodeEnum.UPLOAD_SUCCESS);
    }

}
