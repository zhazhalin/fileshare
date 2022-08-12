package com.zhang.fileshare.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (User)实体类
 *
 * @author makejava
 * @since 2022-08-10 15:48:34
 */
public class User implements Serializable {
    private static final long serialVersionUID = -80834015704010565L;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 用户姓名
     */
    private String userName;
    /**
     * 用户昵称
     */
    private String userNickname;
    /**
     * 用户密码
     */
    private String userPwd;
    /**
     * 盐值
     */
    private String userSalt;
    /**
     * 手机号
     */
    private String userPhone;
    /**
     * 邮箱
     */
    private String userEmail;
    /**
     * QQ
     */
    private String userQq;
    /**
     * 性别
     */
    private String userSex;
    /**
     * 出生日期
     */
    private String userBirthday;
    /**
     * 头像
     */
    private String userImg;
    /**
     * 创建时间
     */
    private String createtime;
    /**
     * 更新时间
     */
    private Date updatetime;
    /**
     * 删除时间
     */
    private Date deletetime;
    /**
     * 逻辑删除
     */
    private String isDeleted;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserSalt() {
        return userSalt;
    }

    public void setUserSalt(String userSalt) {
        this.userSalt = userSalt;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserQq() {
        return userQq;
    }

    public void setUserQq(String userQq) {
        this.userQq = userQq;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(String userBirthday) {
        this.userBirthday = userBirthday;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Date getDeletetime() {
        return deletetime;
    }

    public void setDeletetime(Date deletetime) {
        this.deletetime = deletetime;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

}
