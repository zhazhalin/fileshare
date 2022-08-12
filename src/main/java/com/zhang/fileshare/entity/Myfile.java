package com.zhang.fileshare.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Myfile)实体类
 *
 * @author makejava
 * @since 2022-08-11 16:04:10
 */
public class Myfile implements Serializable {
    private static final long serialVersionUID = 820380742184037531L;
    /**
     * 文件编号
     */
    private Long fileId;
    /**
     * 文件类型
     */
    private String fileContenttype;
    /**
     * 文件名称
     */
    private String fileName;
    /**
     * 文件路径
     */
    private String fileUrl;
    /**
     * 创建时间
     */
    private Date createtime;
    /**
     * 更新时间
     */
    private Date updatetime;
    /**
     * 是否删除0：未删除1：已删除
     */
    private String isDeleted;
    /**
     * 下载次数
     */
    private Integer dowloadCount;
    /**
     * 文件描述
     */
    private String description;
    /**
     * 文件缩略图
     */
    private String fileImg;
    /**
     * 文件大小
     */
    private Long fileSize;
    /**
     * 文件所有者
     */
    private Long fileUser;
    /**
     * 0非公开1公开
     */
    private String isShare;


    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public String getFileContenttype() {
        return fileContenttype;
    }

    public void setFileContenttype(String fileContenttype) {
        this.fileContenttype = fileContenttype;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Integer getDowloadCount() {
        return dowloadCount;
    }

    public void setDowloadCount(Integer dowloadCount) {
        this.dowloadCount = dowloadCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFileImg() {
        return fileImg;
    }

    public void setFileImg(String fileImg) {
        this.fileImg = fileImg;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public Long getFileUser() {
        return fileUser;
    }

    public void setFileUser(Long fileUser) {
        this.fileUser = fileUser;
    }

    public String getIsShare() {
        return isShare;
    }

    public void setIsShare(String isShare) {
        this.isShare = isShare;
    }

}
