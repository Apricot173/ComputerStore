package com.apricot.store.Interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor  implements HandlerInterceptor {
    /**
     *
     * 检测全局session对象中是否有uid，如果有则表示用户已登录，放行，否则重定向到登录页面
     * param request 请求对象
     * param response 响应对象
     * param handler 处理器：映射
     * return boolean 是否放行
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        Object uid = request.getSession().getAttribute("uid");
        if (uid == null) {
            response.sendRedirect( "/web/login.html");
            return false;
        }
        return true;
    }
}
