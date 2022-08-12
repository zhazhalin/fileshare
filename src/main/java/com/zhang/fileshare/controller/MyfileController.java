package com.zhang.fileshare.controller;

import com.zhang.fileshare.entity.Myfile;
import com.zhang.fileshare.service.MyfileService;
import com.zhang.fileshare.utils.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Myfile)表控制层
 *
 * @author makejava
 * @since 2022-08-11 16:04:12
 */
@Slf4j
@RestController
@RequestMapping("/myfile")
@Api("文件操作")
public class MyfileController {
    /**
     * 服务对象
     */
    @Resource
    private MyfileService myfileService;

    /**
     * 通过type查询分类数据
     *
     * @param type 文件类型
     * @return 返回条件查询的结果集
     */
    @GetMapping("/queryByType/{type}")
    @ApiOperation("根据类型查询所有共享文件")
    public Result<List<Myfile>> queryByType(@PathVariable("type") String type) {
        return myfileService.queryByType(type);
    }

    /**
     * 根据文件名称进行搜索
     *
     * @param fileName
     * @return
     */
    @GetMapping("/searchFile/{fileName}")
    @ApiOperation("根据文件名称进行搜索")
    public Result searchFile(@PathVariable("fileName") String fileName) {
        List<Myfile> myfiles = myfileService.searchFile(fileName);
        return Result.ok(myfiles);
    }

    /**
     * 预览文件
     *
     * @param fileId
     * @return 返回文件地址
     */
    @GetMapping("/preview/{fileId}")
    @ApiOperation("预览文件")
    public Result previewFile(@PathVariable("fileId") Long fileId) {
        Myfile myfile = myfileService.queryById(fileId);
        return Result.ok(myfile.getFileUrl());
    }

    /**
     * 将文件放入回收站
     *
     * @param fileId
     * @return
     */
    @GetMapping("/reclaim/{fileId}")
    @ApiOperation("将文件放入回收站")
    public Result reclaim(@PathVariable("fileId") Long fileId) {
        Myfile myfile = new Myfile();
        myfile.setIsDeleted("1");
        Myfile update = myfileService.update(myfile);
        if (update.getIsDeleted() == "1") //1为放入回收站
            return Result.ok("您的文件已放置回收站");
        else
            return Result.fail("删除失败！");
    }

    /**
     * 查看回收站文件
     *
     * @param userId
     * @return
     */
    @GetMapping("/showReclaim/{userId}/{page}/{limit}")
    @ApiOperation("查询用户回收站文件")
    public Result showReclaim(@PathVariable("userId") Long userId, @PathVariable("page") Integer page, @PathVariable("limit") Integer limit) {
        Myfile myfile = new Myfile();
        myfile.setFileUser(userId); //查询指定用户的回收站文件
        myfile.setIsDeleted("1");//查询已经删除的文件
        List<Myfile> myfiles = myfileService.queryAllByLimit(myfile, page, limit);
        return Result.ok(myfiles);
    }

    /**
     * 恢复用户回收站文件
     *
     * @param fileId
     * @return
     */
    @GetMapping("/unReclaim/{fileId}")
    @ApiOperation("恢复用户回收站文件")
    public Result unReclaim(@PathVariable("fileId") Long fileId) {
        Myfile myfile = new Myfile();
        myfile.setFileId(fileId);
        myfile.setIsDeleted("0"); //0为未放进回收站
        Myfile update = myfileService.update(myfile);
        if (update != null) {
            return Result.ok("恢复成功");
        }
        return Result.fail("恢复失败");
    }

    /**
     * 查询个人用户私有文件
     *
     * @param userId
     * @return
     */
    @GetMapping("/queryPrivate/{userId}/{page}/{limit}")
    @ApiOperation("查询用户回收站文件")
    public Result queryPrivate(@PathVariable("userId") Long userId, @PathVariable("page") Integer page, @PathVariable("limit") Integer limit) {
        Myfile myfile = new Myfile();
        myfile.setIsShare("0"); //查询指定用户的私有文件
        myfile.setIsDeleted("0");//查询没有删除删除的文件
        List<Myfile> myfiles = myfileService.queryAllByLimit(myfile, page, limit);
        return Result.ok(myfiles);
    }

    /**
     * 更改文件私有状态
     *
     * @param userId
     * @return
     */
    @GetMapping("/queryPrivate/{fileId}/{isShare}")
    @ApiOperation("查询用户回收站文件")
    public Result queryPrivate(@PathVariable("fileId") Long userId, @PathVariable("isShare") String isShare) {
        Myfile myfile = new Myfile();
        myfile.setIsShare(isShare); //更改文件的私有状态
        myfile.setIsDeleted("0");//更改没有删除的文件
        Myfile update = myfileService.update(myfile);
        if (update != null)
            return Result.ok("更新文件私有状态成功");
        return Result.fail("更新文件私有状态失败");
    }
}
