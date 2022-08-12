package com.zhang.fileshare.utils.result;

import lombok.Getter;

/**
 * 统一返回结果状态信息类
 */
@Getter
public enum ResultCodeEnum {

    SUCCESS(200,"成功"),
    FAIL(201, "失败"),
    PARAM_ERROR( 202, "参数不正确"),
    UPLOAD_SUCCESS(200,"上传成功"),
    UPLOAD_FAIL(204,"上传失败"),
    LOGIN_AUTH(208, "未登陆"),
    PERMISSION(209, "没有权限"),

    CODE_ERROR(210, "验证码错误"),
    LOGIN_DISABLED_ERROR(212, "该用户已被禁用"),
    REGISTER_MOBLE_ERROR(213, "手机号已被使用"),
    LOGIN_AURH(214, "需要登录"),
    LOGIN_ACL(215, "没有权限"),
    ;

    private Integer code;
    private String message;

    private ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
