package com.zhang.fileshare.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (UdRecord)实体类
 *
 * @author makejava
 * @since 2022-08-11 16:06:02
 */
public class UdRecord implements Serializable {
    private static final long serialVersionUID = -93358038598625950L;
    /**
     * 操作ID
     */
    private Long udId;
    /**
     * 操作用户
     */
    private Long userId;
    /**
     * 操作文件
     */
    private Long fileId;
    /**
     * 创建时间
     */
    private Date createtime;
    /**
     * 0：上传1下载
     */
    private Integer option;
    /**
     * 0:公开1非公开
     */
    private String isShare;


    public Long getUdId() {
        return udId;
    }

    public void setUdId(Long udId) {
        this.udId = udId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getOption() {
        return option;
    }

    public void setOption(Integer option) {
        this.option = option;
    }

    public String getIsShare() {
        return isShare;
    }

    public void setIsShare(String isShare) {
        this.isShare = isShare;
    }

}
