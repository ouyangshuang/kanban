package com.lianjia.sh.kanban.controller;

import com.lianjia.sh.kanban.bean.DictEnum;
import com.lianjia.sh.kanban.bean.MessageCodeEnum;
import com.lianjia.sh.kanban.bean.MessageResult;
import com.lianjia.sh.kanban.model.User;
import com.lianjia.sh.kanban.service.DictService;
import com.lianjia.sh.kanban.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * @author ouyang
 * @since 2016-07-10 15:30
 */
@RestController
public class InitController {

    @Autowired
    private UserService userService;

    @Autowired
    private DictService dictService;

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
    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public MessageResult<User> init(
        @RequestParam(required = true, value = "username") String username,
        @RequestParam(required = true, value = "password") String password,
        @RequestParam(required = true, value = "repassword") String repassword,
        @RequestParam(required = true, value = "name") String name,
        @RequestParam(required = true, value = "email") String email,
        @RequestParam(required = true, value = "roleCode") Long roleCode){

        Set<Long> roleCodeSet = dictService.selectCode(DictEnum.角色);

        try {
            Assert.hasLength(username,"username 不能为空");
            Assert.hasLength(password,"username 不能为空");
            Assert.hasLength(repassword,"repassword 不能为空");
            Assert.hasLength(name, "nickname 不能为空");
            Assert.hasLength(email, "mail 不能为空");
            Assert.isTrue(roleCodeSet.contains(roleCode),
                    "角色代码,暂时支持 1000010000(pm 产品经理) 1000010086(se 超级攻城狮) 1000010010(qa 质量评测师) ");
            Assert.isTrue(password.equals(repassword), "密码不一致");

            Assert.isTrue(username.matches("\\S{6,20}"),"username error");
            Assert.isTrue(password.matches("\\S{6,20}"),"password error");
            Assert.isTrue(repassword.matches("\\S{6,20}"),"repassword error");
            Assert.isTrue(name.matches("\\S{1,16}"),"name error");
            Assert.isTrue(email.matches("\\S{10,50}"), "mail error");

            int userCount = userService.count();

            if(userCount > 1){
                return MessageResult.fail(null, MessageCodeEnum.初始化失败, "已经初始化了,登录数据库看看吧...");
            }

            User user = userService.insert(username, password, repassword, name, email, roleCode, 1);

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
