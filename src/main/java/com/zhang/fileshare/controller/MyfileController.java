package com.zhang.fileshare.controller;

import com.zhang.fileshare.entity.Myfile;
import com.zhang.fileshare.service.MyfileService;
import com.zhang.fileshare.utils.result.Result;
import org.springframework.web.bind.annotation.*;

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
public class MyfileController {
    /**
     * 服务对象
     */
    @Resource
    private MyfileService myfileService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/queryByType")
    public Result queryByType(String type) {
        return myfileService.queryByType(type);
    }

}
