package com.lianjia.sh.kanban.dao;

import com.lianjia.sh.kanban.model.User;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ouyang
 * @since 2016-07-10 17:11
 */
@Repository
public class UserDao {

    @Autowired
    private SqlSession sqlSession;

    public Integer insert(String username, String passwordMd5) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("username", username);
        paramMap.put("password", passwordMd5);

        sqlSession.insert("UserMapper.insert", paramMap);

        return (Integer) paramMap.get("id");
    }

    public int count() {
        return sqlSession.selectOne("UserMapper.count");
    }

    public User select(String username, String passwordMd5) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("username", username);
        paramMap.put("password", passwordMd5);

        return sqlSession.selectOne("UserMapper.select",paramMap);
    }
}
