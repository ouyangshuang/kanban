package com.lianjia.sh.kanban.login;

import com.lianjia.sh.kanban.bean.MessageCodeEnum;
import com.lianjia.sh.kanban.bean.MessageResult;
import com.lianjia.sh.kanban.model.User;
import com.lianjia.sh.kanban.service.UserService;
import net.rubyeye.xmemcached.exception.MemcachedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author ouyang
 * @since 2016-01-30 17:41
 */
@RestController
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private LoginService loginFactory;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public MessageResult<User> login(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password,
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {

        User user = userService.select(username, password);

        if (user == null){
            return MessageResult.fail(null, MessageCodeEnum.账号或者密码错误);
        }

        if (user != null) {
            try {
                loginFactory.login(request, response, user);
            } catch (InterruptedException | MemcachedException | TimeoutException e) {
                LOGGER.error(e.getClass().getName(), e);
                return MessageResult.fail(null,MessageCodeEnum.登录异常);
            }
        }

        return MessageResult.success(user);
    }


}
