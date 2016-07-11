package com.lianjia.sh.kanban.login;

import com.lianjia.sh.kanban.model.Member;
import net.rubyeye.xmemcached.MemcachedClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private MemcachedClient commonMemcachedClient;

    @Override
    public boolean preHandle(
            HttpServletRequest request, HttpServletResponse response,
            Object handler) throws Exception {

        String tokenKey = null;

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(LoginService.COOKIE_LOGIN_KEY)) {
                    tokenKey = cookie.getValue();
                }
            }
        }
        if (tokenKey != null) {
            Member member = commonMemcachedClient.getAndTouch(tokenKey, LoginService.TOKEN_TIMEOUT);
            if (member == null) {
                response.sendRedirect(LoginService.LOGIN_URL);
                return false;
            }
        } else {
            response.sendRedirect(LoginService.LOGIN_URL);
            return false;
        }

        return true;
    }

}
