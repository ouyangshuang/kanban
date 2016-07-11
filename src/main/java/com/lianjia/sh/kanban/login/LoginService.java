package com.lianjia.sh.kanban.login;

import com.lianjia.sh.kanban.framework.UUID;
import com.lianjia.sh.kanban.model.Member;
import com.lianjia.sh.kanban.model.User;
import com.lianjia.sh.kanban.service.MemberService;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ouyang
 * @since 2016-02-18 16:28
 */
@Component
public class LoginService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginService.class);

    public static String COOKIE_LOGIN_KEY = "3f6b99e0";

    public static int COOKIE_TIMEOUT = 60 * 60 * 10;

    public static String LOGIN_URL = "/login";

    public static int TOKEN_TIMEOUT = 60 * 30;

    @Autowired
    private MemcachedClient commonMemcachedClient;

    @Autowired
    private MemberService memberService;

    public boolean login(
            HttpServletRequest request,
            HttpServletResponse response,
            User user) throws IOException, InterruptedException, MemcachedException, TimeoutException {

        String tokenKey = UUID.randomUUID();

        Member member = memberService.select(user.getId());

        commonMemcachedClient.set(tokenKey, LoginService.TOKEN_TIMEOUT, member);
        Cookie cookie = new Cookie(LoginService.COOKIE_LOGIN_KEY, tokenKey);
        cookie.setMaxAge(COOKIE_TIMEOUT);
        cookie.setPath("/");
        cookie.setDomain("." + getDomain(request.getServerName()));
        response.addCookie(cookie);
        return true;
    }


    public boolean logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(LoginService.COOKIE_LOGIN_KEY)) {
                    cookie.setMaxAge(0);
                    cookie.setPath("/");
                    cookie.setDomain("." + getDomain(request.getServerName()));
                    response.addCookie(cookie);
                }
            }
        }
        response.sendRedirect(LoginService.LOGIN_URL);
        return false;
    }


    public String getDomain(String url) {
        Pattern patt = Pattern.compile(
                "[\\S]*?\\.([\\w-]+\\.(com|net|cn|gov|cn|org|name|info|biz|cc|tv|me|co|so|tel|mobi|asia))[\\S]*",
                Pattern.CASE_INSENSITIVE);
        Matcher match = patt.matcher(url);
        if (match.matches()) {
            return match.group(1);
        }
        return "";
    }


    public Member getMember(HttpServletRequest request){
        Member member = null;
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
            try {
                member = commonMemcachedClient.getAndTouch(tokenKey, LoginService.TOKEN_TIMEOUT);
            } catch (TimeoutException|InterruptedException|MemcachedException e) {
                LOGGER.error(e.getClass().getName(), e);
            }
        }
        return member;
    }

}
