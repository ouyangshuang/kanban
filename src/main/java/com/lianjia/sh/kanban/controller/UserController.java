package com.lianjia.sh.kanban.controller;

import com.lianjia.sh.kanban.bean.DictEnum;
import com.lianjia.sh.kanban.bean.MessageCodeEnum;
import com.lianjia.sh.kanban.bean.MessageResult;
import com.lianjia.sh.kanban.login.LoginService;
import com.lianjia.sh.kanban.model.Member;
import com.lianjia.sh.kanban.model.User;
import com.lianjia.sh.kanban.service.DictService;
import com.lianjia.sh.kanban.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author ouyang
 * @since 2016-07-07 16:19
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private DictService dictService;

    @Autowired
    private LoginService loginService;

    /**
     * 初始化第一个用户
     * @param username 登录用户名
     * @param password 密码
     * @param repassword 重复密码
     * @param name 成员名称
     * @param email email
     * @param roleCode 角色代码,暂时支持 1000010000 1000010086 1000010010
     * @return
     */
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public MessageResult<User> init(
            HttpServletRequest request,
            @RequestParam(required = true, value = "username") String username,
            @RequestParam(required = true, value = "password") String password,
            @RequestParam(required = true, value = "repassword") String repassword,
            @RequestParam(required = true, value = "name") String name,
            @RequestParam(required = true, value = "email") String email,
            @RequestParam(required = true, value = "roleCode") Long roleCode){

        Member member = loginService.getMember(request);

        Map<String,String> roleMap = dictService.selectKeyMap(DictEnum.角色);

        try {
            Assert.hasLength(username, "username 不能为空");
            Assert.hasLength(password,"username 不能为空");
            Assert.hasLength(repassword,"repassword 不能为空");
            Assert.hasLength(name, "nickname 不能为空");
            Assert.hasLength(email, "mail 不能为空");
            Assert.isTrue(roleMap.containsKey(roleCode.toString()),
                    "角色代码,暂时支持 1000010000(pm 产品经理) 1000010086(se 超级攻城狮) 1000010010(qa 质量评测师) ");
            Assert.isTrue(password.equals(repassword), "密码不一致");

            Assert.isTrue(username.matches("\\S"),"username error");
            Assert.isTrue(password.matches("\\S"),"password error");
            Assert.isTrue(repassword.matches("\\S"),"repassword error");
            Assert.isTrue(name.matches("\\S"),"name error");
            Assert.isTrue(email.matches("\\S"), "mail error");

            User user = userService.insert(username, password, repassword, name, email, roleCode, member.getId());

            if(user != null){
                user.setPassword(password.replaceAll("\\S", "*"));
                return MessageResult.success(user);
            }else {
                return MessageResult.fail(null, MessageCodeEnum.初始化失败, "未知错误");
            }

        }catch (IllegalArgumentException e){
            return MessageResult.fail(null, MessageCodeEnum.初始化失败, e.getMessage());
        }
    }


}
