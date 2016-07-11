package com.lianjia.sh.kanban.service;

import com.lianjia.sh.kanban.dao.MemberDao;
import com.lianjia.sh.kanban.dao.UserDao;
import com.lianjia.sh.kanban.framework.MD5;
import com.lianjia.sh.kanban.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @author ouyang
 * @since 2016-07-08 15:24
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private MemberDao memberDao;


    public User select(String username, String password) {
        String passwordMd5 = MD5.get(password);
        return userDao.select(username,passwordMd5);
    }

    public int count() {
        return userDao.count();
    }

    public User insert(
            String username, String password, String repassword, String name, String email, Long roleCode, Integer cm) {

        Assert.isTrue(password.equals(repassword));

        String passwordMd5 = MD5.get(password);

        Integer userId = userDao.insert(username, passwordMd5);

        Assert.notNull(userId);

        Integer memberId = memberDao.insert(userId,name,email,cm);

        Assert.notNull(memberId);

        return this.select(username, password);

    }
}
