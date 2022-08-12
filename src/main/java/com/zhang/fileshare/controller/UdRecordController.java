package com.zhang.fileshare.controller;

import com.zhang.fileshare.entity.Myfile;
import com.zhang.fileshare.entity.UdRecord;
import com.zhang.fileshare.service.MyfileService;
import com.zhang.fileshare.service.UdRecordService;
import com.zhang.fileshare.utils.oss.OssUtils;
import com.zhang.fileshare.utils.result.Result;
import com.zhang.fileshare.utils.token.JwtHelper;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
    @Autowired
    private MyfileService myfileService;


    /**
     * 上传文件到阿里云oss存储
     * @param file
     * @return
     */
    @PostMapping("/upload/{isShare}/{description}")
    @ApiOperation("根据用户上传文件信息将文件上传至阿里云并将其文件信息存至数据库")
    public Result upload(HttpServletRequest request,@PathVariable("isShare")String isShare, @PathVariable("description")String description,MultipartFile file){
        Result result=udRecordService.upload(request,isShare,description,file);
        return result;
    }

    /**
     * 下载操作
     * @param fileIds
     * @param request
     * @param response
     * @throws IOException
     */
    @GetMapping("/download")
    @ApiImplicitParam(name = "fileIds", value = "ID集合", required = true, allowMultiple=true, dataType="Long", paramType = "query")
    public void downloadOrPreview(@RequestParam("fileIds") Long[] fileIds,HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<String> list=new ArrayList<>();
        List<Myfile> myfiles=new ArrayList<>();
        for (Long aLong : fileIds) {
            Myfile myfile = myfileService.queryById(aLong);
            String str=myfile.getFileUrl().replaceAll("https://atguigu-yygh-zhazhalin.oss-cn-chengdu.aliyuncs.com/","");
            myfiles.add(myfile);
            list.add(str);

        }
        String zip= UUID.randomUUID().toString();
        OssUtils.batchDownLoadOssFile(list,zip,response);
        //存入下载日志
        for (Myfile myfile : myfiles) {
            UdRecord udRecord=new UdRecord();
            udRecord.setCreatetime(new DateTime().toDate());
            udRecord.setOption(1);
            udRecord.setUserId(JwtHelper.getUserId(request.getHeader("token")));
            udRecord.setFileId(myfile.getFileId());
            udRecordService.insert(udRecord); //将记录插入日志表
        }

    }
}
