package com.zhang.fileshare.config;

import com.alibaba.fastjson.JSONObject;
import com.zhang.fileshare.utils.token.JwtHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhazhalin
 * @version 1.0
 * @date 2022/8/11 14:03
 */
@Component
@Slf4j
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //跨域请求会首先发一个option请求，直接返回正常状态并通过拦截器
        if(request.getMethod().equals("OPTIONS")){
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }
        response.setCharacterEncoding("utf-8");
        String token = request.getHeader("token");
        if (token!=null){
            if (JwtHelper.verify(token)){
                System.out.println("通过拦截器");
                return true;
            }
        }
        response.setContentType("application/json; charset=utf-8");
        try {
            JSONObject json=new JSONObject();
            json.put("msg","您未登录，请重新登录！");
            json.put("code","500");
            response.getWriter().append(json.toString());
            log.info("token验证失败！");
        } catch (Exception e) {
            return false;
        }
        return false;
    }
}
