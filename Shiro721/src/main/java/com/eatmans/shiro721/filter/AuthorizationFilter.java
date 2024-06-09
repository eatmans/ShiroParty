package com.eatmans.shiro721.filter;

import com.alibaba.fastjson.JSON;
import com.eatmans.shiro721.restful.JsonResult;
import com.eatmans.shiro721.restful.ResponseCode;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 鉴权过滤器
 */
@WebFilter
public class AuthorizationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        String requestURI = request.getRequestURI();
        Subject subject = SecurityUtils.getSubject();

        if (subject != null && !subject.isPermitted(requestURI)) {
            HttpServletResponse response = (HttpServletResponse) resp;
            response.setContentType("application/json;charset=utf-8");

            // 构建返回对象
            JsonResult<Void> jsonResult= JsonResult.error(ResponseCode.UNAUTHORIZED, "正在访问未授权的资源");
            String data = JSON.toJSONString(jsonResult);

            response.getWriter().write(data);
            return;
        }

        chain.doFilter(req, resp);
    }

}