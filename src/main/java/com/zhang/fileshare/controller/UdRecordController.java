package com.zhang.fileshare.controller;

import com.zhang.fileshare.service.UdRecordService;
import com.zhang.fileshare.utils.result.Result;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * (UdRecord)表控制层
 *
 * @author makejava
 * @since 2022-08-11 16:06:05
 */
@RestController
@RequestMapping("udRecord")
public class UdRecordController {
    /**
     * 服务对象
     */
    @Resource
    private UdRecordService udRecordService;


    /**
     * 上传文件到阿里云oss存储
     * @param file
     * @return
     */
    @PostMapping("/upload/{isShare}/{description}")
    public Result upload(HttpServletRequest request,@PathVariable("isShare")String isShare, @PathVariable("description")String description,MultipartFile file){
        Result result=udRecordService.upload(request,isShare,description,file);
        return result;
    }

}
