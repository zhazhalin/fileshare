package com.zhang.fileshare.controller;

import com.zhang.fileshare.entity.Myfile;
import com.zhang.fileshare.service.MyfileService;
import com.zhang.fileshare.utils.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
     * @param type 文件类型
     * @return 返回条件查询的结果集
     */
    @GetMapping("/queryByType/{type}")
    @ApiOperation("根据类型查询文件")
    public Result<List<Myfile>> queryByType(@PathVariable("type") String type) {
        return myfileService.queryByType(type);
    }

}
