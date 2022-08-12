package com.zhang.fileshare.utils.token;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhazhalin
 * @version 1.0
 * @date 2022/2/26 11:02
 */
public class AuthContextHelper {
    //获取用户名称
    public static Long getUserId(HttpServletRequest request){
        //从用户请求头中获取token
        String token = request.getHeader("token");
        //使用jwt工具类进行解析
        Long userId = JwtHelper.getUserId(token);
        return userId;
    }
    //获取当前用户名称
    public static String getUserName(HttpServletRequest request){
        //从用户请求头中获取token
        String token = request.getHeader("token");
        //使用jwt工具类进行解析
        String userName = JwtHelper.getUserName(token);
        return userName;
    }
}
